package shop.goodcasting.api.file.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jcodec.api.awt.FrameGrab;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.NIOUtils;
import org.jcodec.common.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.file.repository.FileRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepo;

    public void extractVideoThumbnail(File file) throws Exception {
        SeekableByteChannel byteChannel = NIOUtils.readableFileChannel(file);
        MP4Demuxer dm = new MP4Demuxer(byteChannel);
        DemuxerTrack vt = dm.getVideoTrack();

        String fileName = file.getAbsolutePath();
        fileName = fileName.substring(0, fileName.lastIndexOf(".")) +".jpg";

        File imageFile = new File(fileName);

        double frameNumber = 0d;

        frameNumber = vt.getMeta().getTotalDuration() / 5.0;

        log.info(frameNumber);

        Picture frame = FrameGrab.getNativeFrame(file, frameNumber);

        log.info(frame);

        BufferedImage img = AWTUtil.toBufferedImage(frame);

        File imgFile = new File(fileName);

        ImageIO.write(img, "jpg", imgFile);
    }



    public void deleteFile(String fileName) {
        File deleteFile = new File(fileName);

        if(deleteFile.exists()) {
            deleteFile.delete();
            System.out.println("파일을 삭제하였습니다.");
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }

    public void deleteFileByProfileId(Long profileId) {
        fileRepo.selectFileIdsByProfileId(profileId)
                .forEach(fileRepo::deleteByProfileId);
    }
}