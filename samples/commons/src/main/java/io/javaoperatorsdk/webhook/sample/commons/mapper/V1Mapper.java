package io.javaoperatorsdk.webhook.sample.commons.mapper;

import io.javaoperatorsdk.webhook.conversion.Mapper;
import io.javaoperatorsdk.webhook.conversion.TargetVersion;
import io.javaoperatorsdk.webhook.sample.commons.customresource.*;

@TargetVersion("v1")
public class V1Mapper implements Mapper<MultiVersionCustomResource, MultiVersionHub> {

  public static final String DEFAULT_ADDITIONAL_VALUE = "default_additional_value";

  @Override
  public MultiVersionHub toHub(MultiVersionCustomResource resource) {
    var hub = new MultiVersionHub();
    hub.setMetadata(resource.getMetadata());
    hub.setValue(resource.getSpec().getValue());
    return hub;
  }

  @Override
  public MultiVersionCustomResource fromHub(MultiVersionHub hub) {
    var res = new MultiVersionCustomResource();
    res.setMetadata(hub.getMetadata());

    var spec = new MultiVersionCustomResourceSpec();
    spec.setValue(hub.getValue());
    res.setSpec(spec);
    return res;
  }
}
