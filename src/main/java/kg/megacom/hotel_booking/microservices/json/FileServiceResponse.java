package kg.megacom.hotel_booking.microservices.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileServiceResponse {

    private  String fileName;
    private String downloadUri;
    private String fileType;
    private Long size;

    public  FileServiceResponse(String fileName, String downloadUri, String fileType, long size){
        this.fileName = fileName;
        this.downloadUri = downloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
