package elekto.results;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

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
    

    public boolean hasCachedElectionsModelData()
    {
        final boolean cached = (this.electionsModelCachedData != null);
        return cached;
    }
    

    public void cacheElectionsModelData()
        throws IOException
    {
        final boolean hasDataNotAlreadyCached = (this.getElectionsModelFile() != null)
                && (this.electionsModelCachedData == null);
        if (hasDataNotAlreadyCached) {
            this.electionsModelCachedData = this.getElectionsModelFile().getBytes();
        }
    }
    

    public InputStream getCachedElectionsModelData()
        throws IOException
    {
        this.cacheElectionsModelData();
        return new ByteArrayInputStream(this.electionsModelCachedData);
    }
    

    private MultipartFile electionsModelFile;
    
    private byte[] electionsModelCachedData;
    
}
