package elekto.results;

import org.springframework.web.multipart.MultipartFile;

import elekto.results.model.Operation;

public class ResultsInputsForm {

    public MultipartFile getElectionsModelFile()
    {
        return this.electionsModelFile;
    }


    public void setElectionsModelFile(
            final MultipartFile electionsModelFile)
    {
        this.electionsModelFile = electionsModelFile;
    }


    public Operation getOperation()
    {
        return this.operation;
    }


    public void setOperation(
            final Operation operation)
    {
        this.operation = operation;
    }

    private MultipartFile electionsModelFile;

    private Operation operation;

}
