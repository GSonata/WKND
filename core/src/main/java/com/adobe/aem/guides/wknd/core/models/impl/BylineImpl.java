package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import com.adobe.aem.guides.wknd.core.models.Byline;
import com.adobe.cq.wcm.core.components.models.Image;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Byline.class }, resourceType = {
        BylineImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class BylineImpl implements Byline {
    protected static final String RESOURCE_TYPE = "wknd/components/byline";

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;
    
    @ValueMapValue
    private String name;

    @ValueMapValue
    private List<String> occupations;

    private Image image;
    

    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request, request.getResource(), Image.class);
    }

    @Override
    public String getName() {
        return name;
    }

    private Image getImage() {
        return image;
    }

    @Override
    public List<String> getOccupations() {
        if (occupations != null) {
            Collections.sort(occupations);
            return new ArrayList<String>(occupations);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean isEmpty() {
        final Image imageComponent = getImage();

        if(StringUtils.isBlank(name)){
            return true;
        }else if (occupations == null || occupations.isEmpty()){
            return true;
        }else if (imageComponent  == null || StringUtils.isBlank(imageComponent.getSrc())){
            return true;
        }else {
            // todos los componentes estan presentes
            return false;
        }
    }

}
