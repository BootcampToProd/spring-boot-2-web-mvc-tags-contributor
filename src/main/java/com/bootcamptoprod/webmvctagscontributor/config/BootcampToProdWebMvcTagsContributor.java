package com.bootcamptoprod.webmvctagscontributor.config;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsContributor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The type Bootcamp to prod web mvc tags contributor.
 * Useful for adding tags in controller metrics in addition to default tags.
 */
@Component
public class BootcampToProdWebMvcTagsContributor implements WebMvcTagsContributor {
    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable exception) {
        var tags = Tags.empty();

        // Optional tag which will be present in metrics only when the condition is evaluated to true
        if (request.getParameter("user") != null) {
            tags = tags.and(Tag.of("user", request.getParameter("user")));
        }

        // Custom tag which will be present in all the controller metrics
        tags = tags.and(Tag.of("tag", "value"));

        return tags;
    }

    @Override
    public Iterable<Tag> getLongRequestTags(HttpServletRequest request, Object handler) {
        return null;
    }
}
