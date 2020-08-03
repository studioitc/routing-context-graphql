package com.studioitc.test.multitenancy;

import io.quarkus.arc.Arc;
import io.quarkus.arc.ManagedContext;
import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@Unremovable
public class CustomTenantResolver implements TenantResolver {

  @Inject
  RoutingContext context;

  @Override
  public String getDefaultTenantId() {
    return "base";
  }

  @Override
  public String resolveTenantId() {
    HttpServerRequest req = context.request();
    String requestPath = req.path();
    String tenant = req.getHeader("X-Tenant-ID");

    // No tenant specified
    if (tenant == null) {
      tenant = getDefaultTenantId();
    }

    return tenant;
  }

}
