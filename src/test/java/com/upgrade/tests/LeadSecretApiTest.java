package com.upgrade.tests;

import com.upgrade.pojos.lead.LeadSecretRequest;
import com.upgrade.pojos.lead.LeadSecretResponse;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j
public class LeadSecretApiTest extends AbstractTest {

    private UUID loanAppUuid = UUID.fromString("b8096ec7-2150-405f-84f5-ae99864b3e96");
    private String url = "https://credapi.credify.tech/api/brfunnelorch/";
    private static final String MISSING_LOAN_APPLICATION = "MISSING_LOAN_APPLICATION";
    private static final String ABNORMAL = "ABNORMAL";
    private static final String NOT_FOUND = "NOT_FOUND";
    private static final String MESSAGE = "Loan application does not exist.";
    private static final String PERSONAL_LOAN = "PERSONAL_LOAN";

    /*
        Please refer README.md for more details on APT Test
    */
    @Test
    public void leadSecretTest() {
        LeadSecretRequest leadSecretRequest = LeadSecretRequest.builder()
                .loanAppUuid(loanAppUuid)
                .skipSideEffects(true)
                .build();

        LeadSecretResponse response = apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(String.format("%s%s", url, "v2/resume/byLeadSecret"))
                .post(leadSecretRequest, 200)
                .getResponse()
                .as(LeadSecretResponse.class);

        assertThat(response.getLoanAppResumptionInfo().getProductType()).isEqualTo(PERSONAL_LOAN);
        assertThat(response.getLoanAppResumptionInfo().getBorrowerResumptionInfo().getFirstName()).isEqualTo("Benjamin");
        assertThat(response.getOffers()).isNullOrEmpty();
        assertThat(response.getSelectedOffer()).isNull();
        assertThat(response.getRequiredAgreements()).isNullOrEmpty();
        assertThat(response.getResetOptions()).isNotNull();
        assertThat(response.getResetOptions()).hasAtLeastOneElementOfType(String.class);
        assertThat(response.getOriginalLoanApp()).isNull();
    }

    @Test
    public void leadSecretWithInvalidLoanAppUuidTest() {
        loanAppUuid = UUID.randomUUID();
        LeadSecretRequest leadSecretRequest = LeadSecretRequest.builder()
                .loanAppUuid(loanAppUuid)
                .skipSideEffects(true)
                .build();

        LeadSecretResponse response = apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(String.format("%s%s", url, "v2/resume/byLeadSecret"))
                .post(leadSecretRequest, 404)
                .getResponse()
                .as(LeadSecretResponse.class);

        assertThat(response.getCode()).isEqualTo("100001");
        assertThat(response.getCodeName()).isEqualTo(MISSING_LOAN_APPLICATION);
        assertThat(response.getMessage()).isEqualTo(MESSAGE);
        assertThat(response.getRetryable()).isEqualTo("false");
        assertThat(response.getType()).isEqualTo(ABNORMAL);
        assertThat(response.getHttpStatus()).isEqualTo(NOT_FOUND);
    }

}
