package at.htlleonding.healthchecks;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("trigger-failure")
public class HealthManipulator {
    static boolean readinessHealth = true;
    static boolean livenessHealth = true;

    @POST
    @Path("readiness")
    public Response failReadiness() {
        readinessHealth = false;
        return Response.ok().build();
    }

    @POST
    @Path("liveness")
    public Response failLiveness() {
        livenessHealth = false;
        return Response.ok().build();
    }

    @POST
    @Path("reset")
    public Response reset() {
        readinessHealth = true;
        livenessHealth = true;
        return Response.ok().build();
    }
}
