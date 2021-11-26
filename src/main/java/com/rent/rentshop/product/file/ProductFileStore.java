package com.rent.rentshop.product.file;

import com.rent.rentshop.product.domain.ProductImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProductFileStore {

    @Value("${file.dir.product}")
    private String fileDir;
    private Object multiPartFile;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<ProductImage> storeFiles(List<MultipartFile> multiPartFiles) throws IOException {
        List<ProductImage> productImages = new ArrayList<>();
        for (MultipartFile multipartFile : multiPartFiles) {
            if(!multiPartFiles.isEmpty()){
                productImages.add(storeFile(multipartFile));
            }
        }
        return productImages;
    }

    public ProductImage storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String serverFileName = createStoreFileName(originalFileName);

        multipartFile.transferTo(new File(getFullPath(serverFileName)));
        return new ProductImage(originalFileName, serverFileName, fileDir);
    }

    private String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

}
