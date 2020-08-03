package com.studioitc.test.multitenancy;

import helper.PropertiesHelper;
import io.agroal.api.AgroalDataSource;
import io.agroal.api.configuration.supplier.AgroalPropertiesReader;
import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.runtime.customized.QuarkusConnectionProvider;
import io.quarkus.hibernate.orm.runtime.tenant.TenantConnectionResolver;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ApplicationScoped
@Unremovable
public class CustomTenantDataSourceConnectionResolver implements TenantConnectionResolver {
  @Inject
  AgroalDataSource defaultDataSource;

  @Override
  public ConnectionProvider resolve(String tenant) {
    AgroalDataSource datasource = null;

    if(tenant.equals("base")) {
      datasource = defaultDataSource;
    }
    else {
      Map<String,String> props=new HashMap<>();

      Properties prop = PropertiesHelper.getTenantProperties(tenant);
      if(prop != null) {
        props.put(AgroalPropertiesReader.MAX_SIZE, "2");
        props.put(AgroalPropertiesReader.MIN_SIZE, "1");
        props.put(AgroalPropertiesReader.INITIAL_SIZE, "1");
        props.put(AgroalPropertiesReader.MAX_LIFETIME_S, "300");
        props.put(AgroalPropertiesReader.ACQUISITION_TIMEOUT_S, "10");

        props.put(AgroalPropertiesReader.JDBC_URL, prop.getProperty("db.url"));
        props.put(AgroalPropertiesReader.PRINCIPAL, prop.getProperty("db.user"));
        props.put(AgroalPropertiesReader.CREDENTIAL, prop.getProperty("db.password"));

        try {
          datasource = AgroalDataSource.from(new AgroalPropertiesReader()
                  .readProperties(props)
                  .get());
        } catch (SQLException sqle) {
          System.out.println("Cannot configure datasource for tenant '"+tenant+"'");
        }
      } else {
        // Error
        System.out.println("Tenant config not found ("+tenant+")");
      }
    }

    if (datasource != null) {
      return new QuarkusConnectionProvider(datasource);
    } else {
      return null;
    }
  }
}
