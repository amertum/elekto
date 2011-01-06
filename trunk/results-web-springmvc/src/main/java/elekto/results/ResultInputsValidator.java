package elekto.results;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ResultInputsValidator
        implements Validator {

    @Override
    public boolean supports(
            final Class<?> clazz)
    {
        return ResultsInputsForm.class.equals(clazz);
    }


    @Override
    public void validate(
            final Object target,
            final Errors errors)
    {
        final ResultsInputsForm resultsInputsForm = (ResultsInputsForm) target;
        if ((resultsInputsForm.getElectionsModelFile() != null) && resultsInputsForm.getElectionsModelFile().isEmpty()) {
            errors.rejectValue("electionsModelFile", "emptyFile", "empty file");
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultInputsValidator.class);

}
