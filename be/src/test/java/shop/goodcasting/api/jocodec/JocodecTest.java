package shop.goodcasting.api.jocodec;

import lombok.extern.log4j.Log4j2;
import org.jcodec.api.awt.FrameGrab;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.NIOUtils;
import org.jcodec.common.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.scale.AWTUtil;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Log4j2
public class JocodecTest {

    @Test
    public void test() throws Exception {
        File file = new File("C:\\Users\\aa\\Desktop\\test2\\에일리 노래.mp4");

        SeekableByteChannel byteChannel = NIOUtils.readableFileChannel(file);
        MP4Demuxer dm = new MP4Demuxer(byteChannel);
        DemuxerTrack vt = dm.getVideoTrack();

        double frameNumber = 0d;

        frameNumber = vt.getMeta().getTotalDuration() / 5.0;

        log.info(frameNumber);

        Picture frame = FrameGrab.getNativeFrame(file, frameNumber);

        log.info(frame);

        String fileName = file.getAbsolutePath();
        fileName = fileName.substring(0, fileName.lastIndexOf(".")) +".jpg";

        BufferedImage img = AWTUtil.toBufferedImage(frame);

        File imgFile = new File(fileName);

        ImageIO.write(img, "jpg", imgFile );


    }
}
