package io.javaoperatorsdk.admissioncontroller.conversion;

import java.util.concurrent.CompletionStage;

import io.fabric8.kubernetes.api.model.apiextensions.v1.ConversionReview;

public interface AsyncConversionRequestHandler {

  CompletionStage<ConversionReview> handle(ConversionReview admissionRequest);

}
