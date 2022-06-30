package io.javaoperatorsdk.admissioncontroller.admission;

import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import io.javaoperatorsdk.admissioncontroller.admission.mutation.DefaultAdmissionRequestMutator;
import io.javaoperatorsdk.admissioncontroller.admission.mutation.Mutator;
import io.javaoperatorsdk.admissioncontroller.admission.validation.DefaultAdmissionRequestValidator;
import io.javaoperatorsdk.admissioncontroller.admission.validation.Validator;

public class AdmissionController<T extends KubernetesResource> {

  private final AdmissionRequestHandler admissionRequestHandler;

  public AdmissionController(Mutator<T> mutator) {
    this(new DefaultAdmissionRequestMutator<>(mutator));
  }

  public AdmissionController(Validator<T> mutator) {
    this(new DefaultAdmissionRequestValidator<>(mutator));
  }

  public AdmissionController(AdmissionRequestHandler admissionRequestHandler) {
    this.admissionRequestHandler = admissionRequestHandler;
  }

  public AdmissionReview handle(AdmissionReview admissionReview) {
    var response = admissionRequestHandler.handle(admissionReview.getRequest());
    AdmissionReview responseAdmissionReview = new AdmissionReview();
    responseAdmissionReview.setResponse(response);
    response.setUid(admissionReview.getRequest().getUid());
    return responseAdmissionReview;
  }

}
