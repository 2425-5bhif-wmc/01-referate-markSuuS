package at.htlleonding.healthchecks;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import java.util.Random;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return isDbConnectionUp()
                ? HealthCheckResponse.up("my-db-connection")
                : HealthCheckResponse.down("my-db-connection");
    }

    public boolean isDbConnectionUp(){
        return new Random().nextBoolean();
    }
}
