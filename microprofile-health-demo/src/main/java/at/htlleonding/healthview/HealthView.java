package at.htlleonding.healthview;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("health-view")
public class HealthView {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance healthView();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(){
        return Templates.healthView();
    }
}
