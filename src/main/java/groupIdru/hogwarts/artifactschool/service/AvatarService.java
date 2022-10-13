package groupIdru.hogwarts.artifactschool.service;

import groupIdru.hogwarts.artifactschool.model.Avatar;
import groupIdru.hogwarts.artifactschool.model.Student;
import groupIdru.hogwarts.artifactschool.repositiries.AvatarRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


import static java.nio.file.StandardOpenOption.CREATE_NEW;

// домашка 3.5
@Service
@Transactional
public class AvatarService {

    @Value("${students.avatar.dir.path}")
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

        public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.getStudent(studentId);
        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImageData(filePath));
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtension(String fileName) {
        return Objects.nonNull(fileName) ? fileName.substring(fileName.indexOf(".") + 1)
                : StringUtils.EMPTY;
    }

    public byte[] generateImageData(Path filePath) throws IOException{
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()
            ) {
                BufferedImage image = ImageIO.read(bis);
                int height = image.getHeight() / (image.getWidth() / 100);
                BufferedImage data = new BufferedImage(100, height, image.getType());
                Graphics2D graphics = data.createGraphics();
                graphics.drawImage(image, 0, 0, Color.blue, null);
                graphics.dispose();
                ImageIO.write(data, getExtension(filePath.getFileName().toString()), baos);
                return baos.toByteArray();
            }
    }

}
