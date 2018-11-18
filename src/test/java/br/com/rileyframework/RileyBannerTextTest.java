package br.com.rileyframework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class RileyBannerTextTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private static final Riley riley = Riley.getInstance();
    private static final String DEFAULT_BANNER = "\n" +
            "____       ____  _ __              \n" +
            "\\ \\ \\     / __ \\(_) /__  __  __    \n" +
            " \\ \\ \\   / /_/ / / / _ \\/ / / /    \n" +
            " / / /  / _, _/ / /  __/ /_/ /     \n" +
            "/_/_/  /_/ |_/_/_/\\___/\\__, /      \n" +
            "                      /____/       \n";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void deve_exibir_banner_default() throws Exception {
        riley.start();

        assertEquals(DEFAULT_BANNER.trim(), outContent.toString().trim());

        riley.shutDown();
    }

    @Test
    public void dado_um_banner_custom_null_deve_exibir_banner_default() throws Exception {
        riley.bannerText(null).start();

        assertEquals(DEFAULT_BANNER.trim(), outContent.toString().trim());

        riley.shutDown();
    }

    @Test
    public void dado_um_banner_custom_deve_exibir_o_mesmo() throws Exception {
        String customBanner = ">>> MY CUSTOM BANNER <<<";
        riley.bannerText(customBanner).start();

        assertEquals(customBanner.trim(), outContent.toString().trim());

        riley.shutDown();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}
