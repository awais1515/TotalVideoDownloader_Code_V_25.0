package com.appsnipp.trendingapps.Utils;
import android.content.Context;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.net.Uri;
import android.os.Environment;
import com.appsnipp.trendingapps.Models.ServicesModel;
import com.appsnipp.trendingapps.R;
import com.htetznaing.lowcostvideo.Model.XModel;
import java.io.File;

public class Utils
{

    public static final Boolean IsDemoMode=true;

    private static String AppDirName="TotalVideoDownloader";
    public static String RootDirectoryFacebook = "/"+ AppDirName+"/Facebook/";
    public static File RootDirectoryFacebookShow = null;
    public static String RootDirectoryInsta = "/"+AppDirName+"/Insta/";
    public static File RootDirectoryInstaShow = null;
    public static String RootDirectoryLikee = "/"+AppDirName+"/Likee/";
    public static File RootDirectoryLikeeShow = null;
    public static String RootDirectoryRoposo = "/"+AppDirName+"/Roposo/";
    public static File RootDirectoryRoposoShow = null;
    public static String RootDirectoryShareChat = "/"+AppDirName+"/ShareChat/";
    public static File RootDirectoryShareChatShow = null;
    public static String RootDirectoryTikTok = "/"+AppDirName+"/TikTok/";
    public static File RootDirectoryTikTokShow = null;

    public static String RootDirectoryTwitter = "/"+AppDirName+"/Twitter/";

    public static File RootDirectoryGDriveShow = null;
    public static String RootDirectoryGDrive = "/"+AppDirName+"/GDrive/";

    public static File RootDirectorydailymotionShow = null;
    public static String RootDirectorydailymotion = "/"+AppDirName+"/dailymotion/";

    public static File RootDirectoryvimeoShow = null;
    public static String RootDirectoryvimeo = "/"+AppDirName+"/vimeo/";

    public static File RootDirectoryChingariShow = null;
    public static String RootDirectoryChingari = "/"+AppDirName+"/chingari/";

    public static File RootDirectoryRizzleShow = null;
    public static String RootDirectoryRizzle = "/"+AppDirName+"/Rizzle/";

    public static File RootDirectoryJoshShow = null;
    public static String RootDirectoryJosh = "/"+AppDirName+"/Josh/";

    public static File RootDirectoryZiliShow = null;
    public static String RootDirectoryZili = "/"+AppDirName+"/Zili/";
    public static File RootDirectoryMitronShow = null;
    public static String RootDirectoryMitron = "/"+AppDirName+"/Mitron/";

    public static File RootDirectoryTrellShow = null;
    public static String RootDirectoryTrell = "/"+AppDirName+"/Trell/";

    public static File RootDirectoryDubsmashShow = null;
    public static String RootDirectoryDubsmash = "/"+AppDirName+"/Dubsmash/";

    public static File RootDirectoryTrillerShow = null;
    public static String RootDirectoryTriller = "/"+AppDirName+"/Triller/";

    public static File RootDirectoryBoloIndyaShow = null;
    public static String RootDirectoryBoloIndya = "/"+AppDirName+"/BoloIndya/";

    public static File RootDirectoryHindShow = null;
    public static String RootDirectoryHind = "/"+AppDirName+"/Hind/";

    public static File RootDirectoryIFUNNYShow = null;
    public static String RootDirectoryIFUNNY = "/"+AppDirName+"/IFUNNY/";

    public static File RootDirectoryMediafireShow = null;
    public static String RootDirectoryMediafire = "/"+AppDirName+"/Mediafire/";

    public static File RootDirectoryOKRUShow = null;
    public static String RootDirectoryOKRU = "/"+AppDirName+"/OKRU/";

    public static File RootDirectoryVKShow = null;
    public static String RootDirectoryVK = "/"+AppDirName+"/VK/";

    public static File RootDirectorySolidFilesShow = null;
    public static String RootDirectorySolidFiles = "/"+AppDirName+"/SolidFiles/";

    public static File RootDirectoryVidozaShow = null;
    public static String RootDirectoryVidoza = "/"+AppDirName+"/Vidoza/";

    public static File RootDirectorySendVidShow = null;
    public static String RootDirectorySendVid = "/"+AppDirName+"/SendVid/";

    public static File RootDirectoryBittubeShow = null;
    public static String RootDirectoryBittube = "/"+AppDirName+"/Bittube/";

    public static File RootDirectorySnackVideoShow = null;
    public static String RootDirectorySnackVideo = "/"+AppDirName+"/SnackVideo/";
    public static File RootDirectoryFunimateShow = null;
    public static String RootDirectoryFunimate = "/"+AppDirName+"/Funimate/";

    public static File RootDirectoryByteShow = null;
    public static String RootDirectoryByte = "/"+AppDirName+"/Byte/";

    public static File RootDirectoryMXTakatakShow = null;
    public static String RootDirectoryMXTakatak = "/"+AppDirName+"/MXTakatak/";

    public static File RootDirectoryFAIRTOKShow = null;
    public static String RootDirectoryFAIRTOK = "/"+AppDirName+"/FAIRTOK/";

    public static File RootDirectoryraaskShow = null;
    public static String RootDirectoryraask = "/"+AppDirName+"/raask/";

    public static File RootDirectoryOJOOShow = null;
    public static String RootDirectoryOJOO = "/"+AppDirName+"/OJOO/";

    public static File RootDirectoryIMGURShow = null;
    public static String RootDirectoryIMGUR = "/"+AppDirName+"/IMGUR/";

    public static File RootDirectoryTUMBLRShow = null;
    public static String RootDirectoryTUMBLR = "/"+AppDirName+"/TUMBLR/";

    public static File RootDirectoryIMDBShow = null;
    public static String RootDirectoryIMDB = "/"+AppDirName+"/IMDB/";

    public static File RootDirectoryPinterestShow = null;
    public static String RootDirectoryPinterest = "/"+AppDirName+"/Pinterest/";

    public static File RootDirectoryFlickrShow = null;
    public static String RootDirectoryFlickr = "/"+AppDirName+"/Flickr/";

    public static File RootDirectoryMOJShow = null;
    public static String RootDirectoryMOJ = "/"+AppDirName+"/MOJ/";

    public static File RootDirectoryREDDITShow = null;
    public static String RootDirectoryREDDIT = "/"+AppDirName+"/REDDIT/";

    public static File RootDirectorySoundcloudShow = null;
    public static String RootDirectorySoundcloud = "/"+AppDirName+"/Soundcloud/";

    public static File RootDirectoryfansubsShow = null;
    public static String RootDirectoryfansubs = "/"+AppDirName+"/fansubs/";

    public static File RootDirectoryespnShow = null;
    public static String RootDirectoryespn = "/"+AppDirName+"/espn/";

    public static File RootDirectorynonegagShow = null;
    public static String RootDirectorynonegag = "/"+AppDirName+"/nonegag/";

    public static File RootDirectorybilibiliShow = null;
    public static String RootDirectorybilibili = "/"+AppDirName+"/bilibili/";

    public static File RootDirectorybloggerShow = null;
    public static String RootDirectoryblogger = "/"+AppDirName+"/blogger/";

    public static File RootDirectoryizleseneShow = null;
    public static String RootDirectoryizlesene = "/"+AppDirName+"/izlesene/";

    public static File RootDirectoryliveleakShow = null;
    public static String RootDirectoryliveleak = "/"+AppDirName+"/liveleak/";

    public static File RootDirectorybitchuteShow = null;
    public static String RootDirectorybitchute = "/"+AppDirName+"/bitchute/";

    public static File RootDirectorylinkedinShow = null;
    public static String RootDirectorylinkedin = "/"+AppDirName+"/linkedin/";

    public static File RootDirectorypopcornflixShow = null;
    public static String RootDirectorypopcornflix = "/"+AppDirName+"/popcornflix/";

    public static File RootDirectorymemeShow = null;
    public static String RootDirectorymeme = "/"+AppDirName+"/meme/";

    public static File RootDirectorykickstarterShow = null;
    public static String RootDirectorykickstarter = "/"+AppDirName+"/kickstarter/";

    public static File RootDirectoryvliveShow = null;
    public static String RootDirectoryvlive = "/"+AppDirName+"/vlive/";

    public static File RootDirectoryvlipsyShow = null;
    public static String RootDirectoryvlipsy = "/"+AppDirName+"/vlipsy/";

    public static File RootDirectoryvidlitShow = null;
    public static String RootDirectoryvidlit = "/"+AppDirName+"/vidlit/";

    public static File RootDirectorygloriatvShow = null;
    public static String RootDirectorygloriatv = "/"+AppDirName+"/gloriatv/";

    public static File RootDirectorywweShow = null;
    public static String RootDirectorywwe = "/"+AppDirName+"/wwe/";

    public static File RootDirectoryaparatShow = null;
    public static String RootDirectoryaparat = "/"+AppDirName+"/aparat/";

    public static File RootDirectoryonetvruShow = null;
    public static String RootDirectoryonetvru = "/"+AppDirName+"/onetvru/";

    public static File RootDirectoryallocineShow = null;
    public static String RootDirectoryallocine = "/"+AppDirName+"/allocine/";

    public static File RootDirectoryveerShow = null;
    public static String RootDirectoryveer = "/"+AppDirName+"/veer/";

    public static File RootDirectorykooappShow = null;
    public static String RootDirectorykooapp = "/"+AppDirName+"/kooapp/";

    public static File RootDirectorystreamableShow = null;
    public static String RootDirectorystreamable = "/"+AppDirName+"/streamable/";

    public static File RootDirectorygfycatShow = null;
    public static String RootDirectorygfycat = "/"+AppDirName+"/gfycat/";

    public static File RootDirectoryfthisShow = null;
    public static String RootDirectoryfthis = "/"+AppDirName+"/fthis/";

    public static File RootDirectoryfireworktvShow = null;
    public static String RootDirectoryfireworktv = "/"+AppDirName+"/fireworktv/";

    public static File RootDirectorycoubShow = null;
    public static String RootDirectorycoub = "/"+AppDirName+"/coub/";

    public static File RootDirectoryrumbleShow = null;
    public static String RootDirectoryrumble = "/"+AppDirName+"/rumble/";

    public static File RootDirectoryfoursharedShow = null;
    public static String RootDirectoryfourshared = "/"+AppDirName+"/fourshared/";

    public static File RootDirectorytedShow = null;
    public static String RootDirectoryted = "/"+AppDirName+"/ted/";

    public static File RootDirectoryfouranimeShow = null;
    public static String RootDirectoryfouranime = "/"+AppDirName+"/fouranime/";

    public static File RootDirectorypdiskShow = null;
    public static String RootDirectorypdisk = "/"+AppDirName+"/pdisk/";

    public static File RootDirectorytikiShow = null;
    public static String RootDirectorytiki = "/"+AppDirName+"/tiki/";

    public static File RootDirectoryLomotifShow = null;
    public static String RootDirectoryLomotif = "/"+AppDirName+"/Lomotif/";

    public static File RootDirectoryBrowserShow = null;
    public static String RootDirectoryBrowser = "/"+AppDirName+"/Browser/";

    public static File RootDirectoryTwitterShow = null;
    public static File RootDirectoryWhatsappShow;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append("/Download/"+ AppDirName+"/Facebook");
        RootDirectoryFacebookShow = new File(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Environment.getExternalStorageDirectory());
        sb2.append("/Download/"+AppDirName+"/Insta");
        RootDirectoryInstaShow = new File(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Environment.getExternalStorageDirectory());
        sb3.append("/Download/"+AppDirName+"/TikTok");
        RootDirectoryTikTokShow = new File(sb3.toString());

        StringBuilder sb4 = new StringBuilder();
        sb4.append(Environment.getExternalStorageDirectory());
        sb4.append("/Download/"+AppDirName+"/Twitter");
        RootDirectoryTwitterShow = new File(sb4.toString());

        StringBuilder sb5 = new StringBuilder();
        sb5.append(Environment.getExternalStorageDirectory());
        sb5.append("/Download/"+AppDirName+"/Whatsapp");
        RootDirectoryWhatsappShow = new File(sb5.toString());
        StringBuilder sb6 = new StringBuilder();
        sb6.append(Environment.getExternalStorageDirectory());
        sb6.append("/Download/"+AppDirName+"/Likee");
        RootDirectoryLikeeShow = new File(sb6.toString());
        StringBuilder sb7 = new StringBuilder();
        sb7.append(Environment.getExternalStorageDirectory());
        sb7.append("/Download/"+AppDirName+"/ShareChat");
        RootDirectoryShareChatShow = new File(sb7.toString());
        StringBuilder sb8 = new StringBuilder();
        sb8.append(Environment.getExternalStorageDirectory());
        sb8.append("/Download/"+AppDirName+"/Roposo");
        RootDirectoryRoposoShow = new File(sb8.toString());

        StringBuilder sb10 = new StringBuilder();
        sb10.append(Environment.getExternalStorageDirectory());
        sb10.append("/Download/"+AppDirName+"/GDrive");
        RootDirectoryGDriveShow = new File(sb10.toString());

        StringBuilder sb11 = new StringBuilder();
        sb11.append(Environment.getExternalStorageDirectory());
        sb11.append("/Download/"+AppDirName+"/dailymotion");
        RootDirectorydailymotionShow = new File(sb11.toString());

        StringBuilder sb12 = new StringBuilder();
        sb12.append(Environment.getExternalStorageDirectory());
        sb12.append("/Download/"+AppDirName+"/vimeo");
        RootDirectoryvimeoShow = new File(sb12.toString());

        StringBuilder sb13 = new StringBuilder();
        sb13.append(Environment.getExternalStorageDirectory());
        sb13.append("/Download/"+AppDirName+"/chingari");
        RootDirectoryChingariShow = new File(sb13.toString());

        StringBuilder sb14 = new StringBuilder();
        sb14.append(Environment.getExternalStorageDirectory());
        sb14.append("/Download/"+AppDirName+"/rizzle");
        RootDirectoryRizzleShow = new File(sb14.toString());


        StringBuilder sb15 = new StringBuilder();
        sb15.append(Environment.getExternalStorageDirectory());
        sb15.append("/Download/"+AppDirName+"/Josh");
        RootDirectoryJoshShow = new File(sb15.toString());

        StringBuilder sb16 = new StringBuilder();
        sb16.append(Environment.getExternalStorageDirectory());
        sb16.append("/Download/"+AppDirName+"/Zili");
        RootDirectoryZiliShow = new File(sb16.toString());

        StringBuilder sb17 = new StringBuilder();
        sb17.append(Environment.getExternalStorageDirectory());
        sb17.append("/Download/"+AppDirName+"/Mitron");
        RootDirectoryMitronShow = new File(sb17.toString());

        StringBuilder sb18 = new StringBuilder();
        sb18.append(Environment.getExternalStorageDirectory());
        sb18.append("/Download/"+AppDirName+"/Trell");
        RootDirectoryTrellShow = new File(sb18.toString());

        StringBuilder sb19 = new StringBuilder();
        sb19.append(Environment.getExternalStorageDirectory());
        sb19.append("/Download/"+AppDirName+"/Dubsmash");
        RootDirectoryDubsmashShow = new File(sb19.toString());

        StringBuilder sb20 = new StringBuilder();
        sb20.append(Environment.getExternalStorageDirectory());
        sb20.append("/Download/"+AppDirName+"/Triller");
        RootDirectoryTrillerShow = new File(sb20.toString());

        StringBuilder sb21 = new StringBuilder();
        sb21.append(Environment.getExternalStorageDirectory());
        sb21.append("/Download/"+AppDirName+"/BoloIndya");
        RootDirectoryBoloIndyaShow = new File(sb21.toString());

        StringBuilder sb22 = new StringBuilder();
        sb22.append(Environment.getExternalStorageDirectory());
        sb22.append("/Download/"+AppDirName+"/Hind");
        RootDirectoryHindShow = new File(sb22.toString());

        StringBuilder sb23 = new StringBuilder();
        sb23.append(Environment.getExternalStorageDirectory());
        sb23.append("/Download/"+AppDirName+"/IFUNNY");
        RootDirectoryIFUNNYShow = new File(sb23.toString());

        StringBuilder sb24 = new StringBuilder();
        sb24.append(Environment.getExternalStorageDirectory());
        sb24.append("/Download/"+AppDirName+"/Mediafire");
        RootDirectoryMediafireShow = new File(sb24.toString());

        StringBuilder sb25 = new StringBuilder();
        sb25.append(Environment.getExternalStorageDirectory());
        sb25.append("/Download/"+AppDirName+"/OKRU");
        RootDirectoryOKRUShow = new File(sb25.toString());

        StringBuilder sb26 = new StringBuilder();
        sb26.append(Environment.getExternalStorageDirectory());
        sb26.append("/Download/"+AppDirName+"/VK");
        RootDirectoryVKShow = new File(sb26.toString());

        StringBuilder sb27 = new StringBuilder();
        sb27.append(Environment.getExternalStorageDirectory());
        sb27.append("/Download/"+AppDirName+"/SolidFiles");
        RootDirectorySolidFilesShow = new File(sb27.toString());

        StringBuilder sb28 = new StringBuilder();
        sb28.append(Environment.getExternalStorageDirectory());
        sb28.append("/Download/"+AppDirName+"/Vidoza");
        RootDirectoryVidozaShow = new File(sb28.toString());

        StringBuilder sb29 = new StringBuilder();
        sb29.append(Environment.getExternalStorageDirectory());
        sb29.append("/Download/"+AppDirName+"/SendVid");
        RootDirectorySendVidShow = new File(sb29.toString());

        StringBuilder sb30 = new StringBuilder();
        sb30.append(Environment.getExternalStorageDirectory());
        sb30.append("/Download/"+AppDirName+"/Bittube");
        RootDirectoryBittubeShow = new File(sb30.toString());

        StringBuilder sb31 = new StringBuilder();
        sb31.append(Environment.getExternalStorageDirectory());
        sb31.append("/Download/"+AppDirName+"/SnackVideo");
        RootDirectorySnackVideoShow = new File(sb31.toString());

        StringBuilder sb32 = new StringBuilder();
        sb32.append(Environment.getExternalStorageDirectory());
        sb32.append("/Download/"+AppDirName+"/Funimate");
        RootDirectoryFunimateShow = new File(sb32.toString());

        StringBuilder sb33 = new StringBuilder();
        sb33.append(Environment.getExternalStorageDirectory());
        sb33.append("/Download/"+AppDirName+"/Byte");
        RootDirectoryByteShow = new File(sb33.toString());

        StringBuilder sb34 = new StringBuilder();
        sb34.append(Environment.getExternalStorageDirectory());
        sb34.append("/Download/"+AppDirName+"/MXTakatak");
        RootDirectoryMXTakatakShow = new File(sb34.toString());

        StringBuilder sb35 = new StringBuilder();
        sb35.append(Environment.getExternalStorageDirectory());
        sb35.append("/Download/"+AppDirName+"/FAIRTOK");
        RootDirectoryFAIRTOKShow = new File(sb35.toString());

        StringBuilder sb36 = new StringBuilder();
        sb36.append(Environment.getExternalStorageDirectory());
        sb36.append("/Download/"+AppDirName+"/raask");
        RootDirectoryraaskShow = new File(sb36.toString());

        StringBuilder sb37 = new StringBuilder();
        sb37.append(Environment.getExternalStorageDirectory());
        sb37.append("/Download/"+AppDirName+"/OJOO");
        RootDirectoryOJOOShow = new File(sb37.toString());

        StringBuilder sb38 = new StringBuilder();
        sb38.append(Environment.getExternalStorageDirectory());
        sb38.append("/Download/"+AppDirName+"/IMGUR");
        RootDirectoryIMGURShow = new File(sb38.toString());

        StringBuilder sb39 = new StringBuilder();
        sb39.append(Environment.getExternalStorageDirectory());
        sb39.append("/Download/"+AppDirName+"/TUMBLR");
        RootDirectoryTUMBLRShow = new File(sb39.toString());

        StringBuilder sb40 = new StringBuilder();
        sb40.append(Environment.getExternalStorageDirectory());
        sb40.append("/Download/"+AppDirName+"/IMDB");
        RootDirectoryIMDBShow = new File(sb40.toString());

        StringBuilder sb41 = new StringBuilder();
        sb41.append(Environment.getExternalStorageDirectory());
        sb41.append("/Download/"+AppDirName+"/Pinterest");
        RootDirectoryPinterestShow = new File(sb41.toString());

        StringBuilder sb42 = new StringBuilder();
        sb42.append(Environment.getExternalStorageDirectory());
        sb42.append("/Download/"+AppDirName+"/Flickr");
        RootDirectoryFlickrShow = new File(sb42.toString());

        StringBuilder sb43 = new StringBuilder();
        sb43.append(Environment.getExternalStorageDirectory());
        sb43.append("/Download/"+AppDirName+"/MOJ");
        RootDirectoryMOJShow = new File(sb43.toString());


        StringBuilder sb44 = new StringBuilder();
        sb44.append(Environment.getExternalStorageDirectory());
        sb44.append("/Download/"+AppDirName+"/REDDIT");
        RootDirectoryREDDITShow = new File(sb44.toString());

        StringBuilder sb45 = new StringBuilder();
        sb45.append(Environment.getExternalStorageDirectory());
        sb45.append("/Download/"+AppDirName+"/Soundcloud");
        RootDirectorySoundcloudShow = new File(sb45.toString());


        StringBuilder sb46 = new StringBuilder();
        sb46.append(Environment.getExternalStorageDirectory());
        sb46.append("/Download/"+AppDirName+"/fansubs");
        RootDirectoryfansubsShow = new File(sb46.toString());

        StringBuilder sb47 = new StringBuilder();
        sb47.append(Environment.getExternalStorageDirectory());
        sb47.append("/Download/"+AppDirName+"/espn");
        RootDirectoryespnShow = new File(sb47.toString());

        StringBuilder sb48 = new StringBuilder();
        sb48.append(Environment.getExternalStorageDirectory());
        sb48.append("/Download/"+AppDirName+"/nonegag");
        RootDirectorynonegagShow = new File(sb48.toString());

        StringBuilder sb49 = new StringBuilder();
        sb49.append(Environment.getExternalStorageDirectory());
        sb49.append("/Download/"+AppDirName+"/bilibili");
        RootDirectorybilibiliShow = new File(sb49.toString());

        StringBuilder sb50 = new StringBuilder();
        sb50.append(Environment.getExternalStorageDirectory());
        sb50.append("/Download/"+AppDirName+"/blogger");
        RootDirectorybloggerShow = new File(sb50.toString());

        StringBuilder sb51 = new StringBuilder();
        sb51.append(Environment.getExternalStorageDirectory());
        sb51.append("/Download/"+AppDirName+"/izlesene");
        RootDirectoryizleseneShow = new File(sb51.toString());

        StringBuilder sb52 = new StringBuilder();
        sb52.append(Environment.getExternalStorageDirectory());
        sb52.append("/Download/"+AppDirName+"/liveleak");
        RootDirectoryliveleakShow = new File(sb52.toString());

        StringBuilder sb53 = new StringBuilder();
        sb53.append(Environment.getExternalStorageDirectory());
        sb53.append("/Download/"+AppDirName+"/bitchute");
        RootDirectorybitchuteShow = new File(sb53.toString());


        StringBuilder sb54 = new StringBuilder();
        sb54.append(Environment.getExternalStorageDirectory());
        sb54.append("/Download/"+AppDirName+"/linkedin");
        RootDirectorylinkedinShow = new File(sb54.toString());


        StringBuilder sb55 = new StringBuilder();
        sb55.append(Environment.getExternalStorageDirectory());
        sb55.append("/Download/"+AppDirName+"/popcornflix");
        RootDirectorypopcornflixShow = new File(sb55.toString());

        StringBuilder sb56 = new StringBuilder();
        sb56.append(Environment.getExternalStorageDirectory());
        sb56.append("/Download/"+AppDirName+"/meme");
        RootDirectorymemeShow = new File(sb56.toString());

        StringBuilder sb57 = new StringBuilder();
        sb57.append(Environment.getExternalStorageDirectory());
        sb57.append("/Download/"+AppDirName+"/kickstarter");
        RootDirectorykickstarterShow = new File(sb57.toString());

        StringBuilder sb58 = new StringBuilder();
        sb58.append(Environment.getExternalStorageDirectory());
        sb58.append("/Download/"+AppDirName+"/vlive");
        RootDirectoryvliveShow = new File(sb58.toString());

        StringBuilder sb59 = new StringBuilder();
        sb59.append(Environment.getExternalStorageDirectory());
        sb59.append("/Download/"+AppDirName+"/vlipsy");
        RootDirectoryvlipsyShow = new File(sb59.toString());

        StringBuilder sb60 = new StringBuilder();
        sb60.append(Environment.getExternalStorageDirectory());
        sb60.append("/Download/"+AppDirName+"/vidlit");
        RootDirectoryvidlitShow = new File(sb60.toString());

        StringBuilder sb61 = new StringBuilder();
        sb61.append(Environment.getExternalStorageDirectory());
        sb61.append("/Download/"+AppDirName+"/gloriatv");
        RootDirectorygloriatvShow = new File(sb61.toString());

        StringBuilder sb62 = new StringBuilder();
        sb62.append(Environment.getExternalStorageDirectory());
        sb62.append("/Download/"+AppDirName+"/wwe");
        RootDirectorywweShow = new File(sb62.toString());

        StringBuilder sb63 = new StringBuilder();
        sb63.append(Environment.getExternalStorageDirectory());
        sb63.append("/Download/"+AppDirName+"/aparat");
        RootDirectoryaparatShow = new File(sb63.toString());

        StringBuilder sb64 = new StringBuilder();
        sb64.append(Environment.getExternalStorageDirectory());
        sb64.append("/Download/"+AppDirName+"/onetvru");
        RootDirectoryonetvruShow = new File(sb64.toString());

        StringBuilder sb65 = new StringBuilder();
        sb65.append(Environment.getExternalStorageDirectory());
        sb65.append("/Download/"+AppDirName+"/allocine");
        RootDirectoryallocineShow = new File(sb65.toString());

        StringBuilder sb66 = new StringBuilder();
        sb66.append(Environment.getExternalStorageDirectory());
        sb66.append("/Download/"+AppDirName+"/veer");
        RootDirectoryveerShow = new File(sb66.toString());

        StringBuilder sb67 = new StringBuilder();
        sb67.append(Environment.getExternalStorageDirectory());
        sb67.append("/Download/"+AppDirName+"/kooapp");
        RootDirectorykooappShow = new File(sb67.toString());

        StringBuilder sb68 = new StringBuilder();
        sb68.append(Environment.getExternalStorageDirectory());
        sb68.append("/Download/"+AppDirName+"/streamable");
        RootDirectorystreamableShow = new File(sb68.toString());


        StringBuilder sb69 = new StringBuilder();
        sb69.append(Environment.getExternalStorageDirectory());
        sb69.append("/Download/"+AppDirName+"/gfycat");
        RootDirectorygfycatShow = new File(sb69.toString());

        StringBuilder sb70 = new StringBuilder();
        sb70.append(Environment.getExternalStorageDirectory());
        sb70.append("/Download/"+AppDirName+"/fthis");
        RootDirectoryfthisShow = new File(sb70.toString());


        StringBuilder sb71 = new StringBuilder();
        sb71.append(Environment.getExternalStorageDirectory());
        sb71.append("/Download/"+AppDirName+"/fireworktv");
        RootDirectoryfireworktvShow = new File(sb71.toString());

        StringBuilder sb72 = new StringBuilder();
        sb72.append(Environment.getExternalStorageDirectory());
        sb72.append("/Download/"+AppDirName+"/coub");
        RootDirectorycoubShow = new File(sb72.toString());

        StringBuilder sb73 = new StringBuilder();
        sb73.append(Environment.getExternalStorageDirectory());
        sb73.append("/Download/"+AppDirName+"/rumble");
        RootDirectoryrumbleShow = new File(sb73.toString());

        StringBuilder sb74 = new StringBuilder();
        sb74.append(Environment.getExternalStorageDirectory());
        sb74.append("/Download/"+AppDirName+"/fourshared");
        RootDirectoryfoursharedShow = new File(sb74.toString());

        StringBuilder sb75 = new StringBuilder();
        sb75.append(Environment.getExternalStorageDirectory());
        sb75.append("/Download/"+AppDirName+"/ted");
        RootDirectorytedShow = new File(sb75.toString());


        StringBuilder sb76 = new StringBuilder();
        sb76.append(Environment.getExternalStorageDirectory());
        sb76.append("/Download/"+AppDirName+"/fouranime");
        RootDirectoryfouranimeShow = new File(sb76.toString());

        StringBuilder sb77 = new StringBuilder();
        sb77.append(Environment.getExternalStorageDirectory());
        sb77.append("/Download/"+AppDirName+"/pdisk");
        RootDirectorypdiskShow = new File(sb77.toString());

        StringBuilder sb78 = new StringBuilder();
        sb78.append(Environment.getExternalStorageDirectory());
        sb78.append("/Download/"+AppDirName+"/tiki");
        RootDirectorytikiShow = new File(sb78.toString());

        StringBuilder sb79 = new StringBuilder();
        sb79.append(Environment.getExternalStorageDirectory());
        sb79.append("/Download/"+AppDirName+"/Lomotif");
        RootDirectoryLomotifShow = new File(sb79.toString());

        StringBuilder sb80 = new StringBuilder();
        sb80.append(Environment.getExternalStorageDirectory());
        sb80.append("/Download/"+AppDirName+"/Browser");
        RootDirectoryBrowserShow = new File(sb80.toString());

    }

    public static ServicesModel GetServicesModel(Services s,Context c)
    {
        ServicesModel servicesModel;
        if(s==Services.ROPOSO)
        {
            servicesModel=new ServicesModel(RootDirectoryRoposoShow,"Roposo",c.getResources().getDrawable(R.drawable.home_ic_roposo),Services.ROPOSO,"https://play.google.com/store/apps/details?id=com.roposo.android&hl=en_IN");
        }
        else if (s==Services.SHARECHAT)
        {
            servicesModel=new ServicesModel(RootDirectoryShareChatShow,"ShareChat",c.getResources().getDrawable(R.drawable.home_ic_sharechat),Services.SHARECHAT,"https://play.google.com/store/apps/details?id=in.mohalla.sharechat&hl=en_IN");
        }
        else if (s==Services.FACEBOOK)
        {
            servicesModel=new ServicesModel(RootDirectoryFacebookShow,"Facebook",c.getResources().getDrawable(R.drawable.home_ic_facebook),Services.FACEBOOK,"https://play.google.com/store/apps/details?id=com.facebook.katana&hl=en_IN");
        }
        else if (s==Services.INSTAGRAM)
        {
            servicesModel=new ServicesModel(RootDirectoryInstaShow,"Instagram",c.getResources().getDrawable(R.drawable.home_ic_instagram),Services.INSTAGRAM,"https://play.google.com/store/apps/details?id=com.instagram.android&hl=en_IN");
        }
        else if (s==Services.TWITTER)
        {
            servicesModel=new ServicesModel(RootDirectoryTwitterShow,"Twitter",c.getResources().getDrawable(R.drawable.home_ic_twitter),Services.TWITTER,RootDirectoryTwitter,"https://play.google.com/store/apps/details?id=com.twitter.android&hl=en_IN");
        }
        else if (s==Services.LIKEE)
        {
            servicesModel=new ServicesModel(RootDirectoryLikeeShow,"Likee",c.getResources().getDrawable(R.drawable.home_ic_likee),Services.LIKEE,"https://play.google.com/store/apps/details?id=video.like&hl=en_IN");
        }
//        else if (s==Services.TIKTOK)
//        {
//            servicesModel=new ServicesModel
//                    (RootDirectoryTikTokShow,
//                            "Tiktok",
//                            c.getResources().getDrawable(R.drawable.home_ic_tiktok),
//                            Services.TIKTOK,
//                            "https://play.google.com/store/apps/details?id=com.zhiliaoapp.musically&hl=en_IN");
//        }
        else if (s==Services.GDRIVE)
        {
            servicesModel=new ServicesModel(RootDirectoryGDriveShow,"Google Drive",c.getResources().getDrawable(R.drawable.home_ic_googledrive),Services.GDRIVE,RootDirectoryGDrive,"https://play.google.com/store/apps/details?id=com.google.android.apps.docs&hl=en_IN");
        }
        else if (s==Services.DAILYMOTION)
        {
            servicesModel=new ServicesModel(RootDirectorydailymotionShow,"DAILYMOTION",c.getResources().getDrawable(R.drawable.home_ic_dailymotion),Services.DAILYMOTION,RootDirectorydailymotion,"https://play.google.com/store/apps/details?id=com.dailymotion.dailymotion&hl=en_IN");
        }
        else if (s==Services.VIMEO)
        {
            servicesModel=new ServicesModel(RootDirectoryvimeoShow,"Vimeo",c.getResources().getDrawable(R.drawable.home_ic_vimeo),Services.VIMEO,RootDirectoryvimeo,"https://play.google.com/store/apps/details?id=com.vimeo.android.videoapp&hl=en_IN");
        }
        else if (s==Services.CHINGARI)
        {
            servicesModel=new ServicesModel(RootDirectoryChingariShow,"Chingari",c.getResources().getDrawable(R.drawable.home_ic_chingari),Services.CHINGARI,RootDirectoryChingari,"https://play.google.com/store/apps/details?id=io.chingari.app&hl=en_IN");
        }
        else if (s==Services.RIZZELE)
        {
            servicesModel=new ServicesModel(RootDirectoryRizzleShow,"Rizzle",c.getResources().getDrawable(R.drawable.home_ic_rizzle),Services.RIZZELE,RootDirectoryRizzle,"https://play.google.com/store/apps/details?id=com.thesilverlabs.rumbl&hl=en_IN");
        }
        else if (s==Services.JOSH)
        {
            servicesModel=new ServicesModel(RootDirectoryJoshShow,"Josh",c.getResources().getDrawable(R.drawable.home_ic_josh),Services.JOSH,RootDirectoryJosh,"https://play.google.com/store/apps/details?id=com.eterno.shortvideos&hl=en_IN");
        }
        else if (s==Services.ZILI)
        {
            servicesModel=new ServicesModel(RootDirectoryZiliShow,"Zili",c.getResources().getDrawable(R.drawable.home_ic_zili),Services.ZILI,RootDirectoryZili,"https://play.google.com/store/apps/details?id=com.funnypuri.client&hl=en_IN");
        }
        else if (s==Services.MITRON)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryMitronShow,
                    "Mitron",
                    c.getResources().getDrawable(R.drawable.home_ic_mitron),
                    Services.MITRON,
                    RootDirectoryMitron,
                    "https://play.google.com/store/apps/details?id=com.mitron.tv&hl=en_IN"
                    );
        }
        else if (s==Services.TRELL)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryTrellShow,
                    "trell",
                    c.getResources().getDrawable(R.drawable.home_ic_trell),
                    Services.TRELL,
                    RootDirectoryTrell,
            "https://play.google.com/store/apps/details?id=app.trell&hl=en_IN"
            );
        }
        else if (s==Services.Dubsmash)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryDubsmashShow,
                    "Dubsmash",
                    c.getResources().getDrawable(R.drawable.home_ic_dubsmash),
                    Services.Dubsmash,
                    RootDirectoryDubsmash,"https://play.google.com/store/apps/details?id=com.mobilemotion.dubsmash&hl=en_IN");
        }
        else if (s==Services.Triller)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryTrillerShow,
                    "Triller",
                    c.getResources().getDrawable(R.drawable.home_ic_triller),
                    Services.Triller,
                    RootDirectoryTriller,"https://play.google.com/store/apps/details?id=co.triller.droid&hl=en_IN");
        }
        else if (s==Services.BoloIndya)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryBoloIndyaShow,
                    "Bolo Indya",
                    c.getResources().getDrawable(R.drawable.home_ic_boloindya),
                    Services.BoloIndya,
                    RootDirectoryBoloIndya,"https://play.google.com/store/apps/details?id=com.boloindya.boloindya&hl=en_IN");
        }
        else if (s==Services.HIND)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryHindShow,
                    "Hind",
                    c.getResources().getDrawable(R.drawable.home_ic_hind),
                    Services.HIND,
                    RootDirectoryHind,"https://play.google.com/store/apps/details?id=com.cardfeed.hindapp&hl=en_IN");
        }
        else if (s==Services.IFUNNY)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryIFUNNYShow,
                    "IFunny",
                    c.getResources().getDrawable(R.drawable.home_ic_ifunny),
                    Services.IFUNNY,
                    RootDirectoryIFUNNY,"https://play.google.com/store/apps/details?id=mobi.ifunny&hl=en_IN");
        }
        else if (s==Services.MEDIAFIRE)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryMediafireShow,
                    "Mediafire",
                    c.getResources().getDrawable(R.drawable.home_ic_mediafire),
                    Services.MEDIAFIRE,
                    RootDirectoryMediafire,"https://app.mediafire.com/");
        }
        else if (s==Services.OKRU)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryOKRUShow,
                    "OK.ru",
                    c.getResources().getDrawable(R.drawable.home_ic_okru),
                    Services.OKRU,
                    RootDirectoryOKRU,"https://ok.ru/");
        }
        else if (s==Services.VK)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryVKShow,
                    "Vk.com",
                    c.getResources().getDrawable(R.drawable.home_ic_vk),
                    Services.VK,
                    RootDirectoryVK,"http://vk.com/");
        }
        else if (s==Services.SOLIDFILES)
        {
            servicesModel=new ServicesModel(
                    RootDirectorySolidFilesShow,
                    "SolidFiles",
                    c.getResources().getDrawable(R.drawable.home_ic_solidfiles),
                    Services.SOLIDFILES,
                    RootDirectorySolidFiles,"https://www.solidfiles.com/");
        }
        else if (s==Services.VIDEOZA)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryVidozaShow,
                    "vidoza.net",
                    c.getResources().getDrawable(R.drawable.home_ic_vidoza),
                    Services.VIDEOZA,
                    RootDirectoryVidoza,"https://vidoza.net/");
        }
        else if (s==Services.SENDVID)
        {
            servicesModel=new ServicesModel(
                    RootDirectorySendVidShow,
                    "SendVid.com",
                    c.getResources().getDrawable(R.drawable.home_ic_sendvid),
                    Services.SENDVID,
                    RootDirectorySendVid,"https://SendVid.com");
        }
        else if (s==Services.BITTUBE)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryBittubeShow,
                    "Bittube.Tv",
                    c.getResources().getDrawable(R.drawable.home_ic_bittube),
                    Services.BITTUBE,
                    RootDirectoryBittube,"https://bittube.tv/");
        }
        else if (s==Services.SNACKVIDEO)
        {
            servicesModel=new ServicesModel(
                    RootDirectorySnackVideoShow,
                    "Snack Video",
                    c.getResources().getDrawable(R.drawable.home_ic_snackvideo),
                    Services.SNACKVIDEO,
                    RootDirectorySnackVideo,"https://play.google.com/store/apps/details?id=com.kwai.bulldog&hl=en_IN");
        }
        else if (s==Services.FUNIMATE)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryFunimateShow,
                    "Funimate",
                    c.getResources().getDrawable(R.drawable.home_ic_funnimate),
                    Services.FUNIMATE,
                    RootDirectoryFunimate,"https://play.google.com/store/apps/details?id=com.avcrbt.funimate&hl=en_IN");
        }
        else if (s==Services.BYTE)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryByteShow,
                    "Byte",
                    c.getResources().getDrawable(R.drawable.home_ic_byte),
                    Services.BYTE,
                    RootDirectoryByte,"https://play.google.com/store/apps/details?id=co.byte&hl=en_IN");
        }

        else if (s==Services.MXTAKATAK)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryMXTakatakShow,
                    "MX Takatak",
                    c.getResources().getDrawable(R.drawable.home_ic_mxtakatak),
                    Services.MXTAKATAK,
                    RootDirectoryMXTakatak,"https://play.google.com/store/apps/details?id=com.next.innovation.takatak&hl=en_IN");
        }
        else if (s==Services.FAIRTOK)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryFAIRTOKShow,
                    "FairTok ",
                    c.getResources().getDrawable(R.drawable.home_ic_fairtok),
                    Services.FAIRTOK,
                    RootDirectoryFAIRTOK,"https://play.google.com/store/apps/details?id=com.fairtok&hl=en_IN");
        }
        else if (s==Services.RAASK)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryraaskShow,
                    "RAASK",
                    c.getResources().getDrawable(R.drawable.home_ic_raask),
                    Services.RAASK,
                    RootDirectoryFAIRTOK,"https://play.google.com/store/apps/details?id=com.musicApp.raask&hl=en_IN");
        }
        else if (s==Services.OJOO)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryOJOOShow,
                    "OJOO",
                    c.getResources().getDrawable(R.drawable.home_ic_ojoo),
                    Services.OJOO,
                    RootDirectoryFAIRTOK,"https://play.google.com/store/apps/details?id=com.fun.top.video&hl=en_IN");
        }
        else if (s==Services.IMGUR)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryIMGURShow,
                    "IMGUR",
                    c.getResources().getDrawable(R.drawable.home_ic_imgur),
                    Services.IMGUR,
                    RootDirectoryIMGUR,"https://imgur.com/");
        }
        else if (s==Services.TUMBLR)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryTUMBLRShow,
                    "Tumblr",
                    c.getResources().getDrawable(R.drawable.home_ic_tumblr),
                    Services.TUMBLR,
                    RootDirectoryTUMBLR,"https://www.tumblr.com/");
        }
        else if (s==Services.IMDB)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryIMDBShow,
                    "IMDb",
                    c.getResources().getDrawable(R.drawable.home_ic_imdb),
                    Services.IMDB,
                    RootDirectoryIMDB,"https://www.imdb.com/?ref_=nv_home");
        }
        else if (s==Services.Pinterest)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryPinterestShow,
                    "Pinterest",
                    c.getResources().getDrawable(R.drawable.home_ic_pinterest),
                    Services.Pinterest,
                    RootDirectoryPinterest,"pinterest.com");
        }
        else if (s==Services.Flickr)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryFlickrShow,
                    "Flickr",
                    c.getResources().getDrawable(R.drawable.home_ic_flickr),
                    Services.Flickr,
                    RootDirectoryFlickr,"https://www.flickr.com/");
        }
        else if (s==Services.MOJ)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryMOJShow,
                    "Moj",
                    c.getResources().getDrawable(R.drawable.home_ic_moj),
                    Services.MOJ,
                    RootDirectoryMOJ,
                    "https://play.google.com/store/apps/details?id=in.mohalla.video&hl=en_IN&gl=US");
        }
        else if (s==Services.REDDIT)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryREDDITShow,
                    "Reddit",
                    c.getResources().getDrawable(R.drawable.home_ic_reddit),
                    Services.REDDIT,
                    RootDirectoryREDDIT,
                    "https://www.reddit.com/");
        }
        else if (s==Services.Soundcloud)
        {
            servicesModel=new ServicesModel(
                    RootDirectorySoundcloudShow,
                    "Soundcloud",
                    c.getResources().getDrawable(R.drawable.home_ic_soundcloud),
                    Services.Soundcloud,
                    RootDirectorySoundcloud,
                    "https://soundcloud.com/");
        }
        else if (s==Services.fansubs)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryfansubsShow,
                    "fansubs",
                    c.getResources().getDrawable(R.drawable.home_ic_fansubs),
                    s,
                    RootDirectoryfansubs,
                    "https://fansubs.tv/");
        }
        else if (s==Services.espn)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryespnShow,
                    "espn",
                    c.getResources().getDrawable(R.drawable.home_ic_espn),
                    s,
                    RootDirectoryespn,
                    "https://espn.in/");
        }
        else if (s==Services.nonegag)
        {
            servicesModel=new ServicesModel(
                    RootDirectorynonegagShow,
                    "9gag",
                    c.getResources().getDrawable(R.drawable.home_ic_ninegag),
                    s,
                    RootDirectorynonegag,
                    "https://9gag.com/");
        }
        else if (s==Services.bilibili)
        {
            servicesModel=new ServicesModel(
                    RootDirectorybilibiliShow,
                    "Bilibili",
                    c.getResources().getDrawable(R.drawable.home_ic_bilibili),
                    s,
                    RootDirectorybilibili,
                    "https://www.bilibili.com/");
        }
        else if (s==Services.blogger)
        {
            servicesModel=new ServicesModel(
                    RootDirectorybloggerShow,
                    "Blogger",
                    c.getResources().getDrawable(R.drawable.home_ic_blogger),
                    s,
                    RootDirectoryblogger,
                    "https://www.blogger.com/");
        }
        else if (s==Services.izlesene)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryizleseneShow,
                    "izlesene",
                    c.getResources().getDrawable(R.drawable.home_ic_izlesene),
                    s,
                    RootDirectoryizlesene,
                    "https://www.izlesene.com/");
        }
        else if (s==Services.liveleak)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryliveleakShow,
                    "liveleak",
                    c.getResources().getDrawable(R.drawable.home_ic_liveleak),
                    s,
                    RootDirectoryliveleak,
                    "https://www.liveleak.com/");
        }
        else if (s==Services.bitchute)
        {
            servicesModel=new ServicesModel(
                    RootDirectorybitchuteShow,
                    "bitchute",
                    c.getResources().getDrawable(R.drawable.home_ic_bitchute),
                    s,
                    RootDirectorybitchute,
                    "https://www.bitchute.com/");
        }
        else if (s==Services.linkedin)
        {
            servicesModel=new ServicesModel(
                    RootDirectorylinkedinShow,
                    "linkedin",
                    c.getResources().getDrawable(R.drawable.home_ic_linkedin),
                    s,
                    RootDirectorylinkedin,
                    "https://www.linkedin.com/");
        }
        else if (s==Services.popcornflix)
        {
            servicesModel=new ServicesModel(
                    RootDirectorypopcornflixShow,
                    "popcornflix",
                    c.getResources().getDrawable(R.drawable.home_ic_popcornflix),
                    s,
                    RootDirectorypopcornflix,
                    "https://www.popcornflix.com/");
        }
        else if (s==Services.meme)
        {
            servicesModel=new ServicesModel(
                    RootDirectorymemeShow,
                    "me.me",
                    c.getResources().getDrawable(R.drawable.home_ic_meme),
                    s,
                    RootDirectorymeme,
                    "https://me.me/");
        }
        else if (s==Services.kickstarter)
        {
            servicesModel=new ServicesModel(
                    RootDirectorykickstarterShow,
                    "kickstarter",
                    c.getResources().getDrawable(R.drawable.home_ic_kickstarter),
                    s,
                    RootDirectorykickstarter,
                    "https://kickstarter.com/");
        }
        else if (s==Services.vlive)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryvliveShow,
                    "vlive",
                    c.getResources().getDrawable(R.drawable.home_ic_vlive),
                    s,
                    RootDirectoryvlive,
                    "https://vlive.tv/");
        }
        else if (s==Services.vlipsy)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryvlipsyShow,
                    "vlipsy",
                    c.getResources().getDrawable(R.drawable.home_ic_vlipsy),
                    s,
                    RootDirectoryvlipsy,
                    "https://vlipsy.com/");
        }
        else if (s==Services.vidlit)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryvidlitShow,
                    "vidlit",
                    c.getResources().getDrawable(R.drawable.home_ic_vidlit),
                    s,
                    RootDirectoryvidlit,
                    "https://vidlit.com/");
        }
        else if (s==Services.gloriatv)
        {
            servicesModel=new ServicesModel(
                    RootDirectorygloriatvShow,
                    "gloriatv",
                    c.getResources().getDrawable(R.drawable.home_ic_gloriatv),
                    s,
                    RootDirectorygloriatv,
                    "https://gloria.tv/");
        }
        else if (s==Services.wwe)
        {
            servicesModel=new ServicesModel(
                    RootDirectorywweShow,
                    "wwe",
                    c.getResources().getDrawable(R.drawable.home_ic_wwe),
                    s,
                    RootDirectorywwe,
                    "https://wwe.com/");
        }
        else if (s==Services.aparat)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryaparatShow,
                    "aparat",
                    c.getResources().getDrawable(R.drawable.home_ic_aparat),
                    s,
                    RootDirectoryaparat,
                    "https://aparat.com/");
        }
        else if (s==Services.onetvru)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryonetvruShow,
                    "1tv.ru",
                    c.getResources().getDrawable(R.drawable.home_ic_onetvru),
                    s,
                    RootDirectoryonetvru,
                    "https://1tv.ru/");
        }
        else if (s==Services.allocine)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryallocineShow,
                    "allocine.fr",
                    c.getResources().getDrawable(R.drawable.home_ic_allocine),
                    s,
                    RootDirectoryallocine,
                    "https://allocine.fr/");
        }
        else if (s==Services.veer)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryveerShow,
                    "veer",
                    c.getResources().getDrawable(R.drawable.home_ic_veer),
                    s,
                    RootDirectoryveer,
                    "https://play.google.com/store/apps/details?id=com.veer.video");
        }
        else if (s==Services.kooapp)
        {
            servicesModel=new ServicesModel(
                    RootDirectorykooappShow,
                    "Koo App",
                    c.getResources().getDrawable(R.drawable.home_ic_kooapp),
                    s,
                    RootDirectorykooapp,
                    "https://play.google.com/store/apps/details?id=com.koo.app&hl=en_IN&gl=US");
        }
        else if (s==Services.TIKTOK)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryTikTokShow,
                    "Tiktok",
                    c.getResources().getDrawable(R.drawable.home_ic_tiktok),
                    Services.TIKTOK,
                    RootDirectoryTikTok,
                    "https://play.google.com/store/apps/details?id=com.zhiliaoapp.musically&hl=en_IN");
        }

        else if (s==Services.streamable)
        {
            servicesModel=new ServicesModel(
                    RootDirectorystreamableShow,
                    "Streamable",
                    c.getResources().getDrawable(R.drawable.home_ic_streamable),
                    s,
                    RootDirectorystreamable,
                    "https://streamable.com/");
        }
        else if (s==Services.gfycat)
        {
            servicesModel=new ServicesModel(
                    RootDirectorygfycatShow,
                    "gfycat",
                    c.getResources().getDrawable(R.drawable.home_ic_gfycat),
                    s,
                    RootDirectorygfycat,
                    "https://gfycat.com/");
        }
        else if (s==Services.fthis)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryfthisShow,
                    "fthis.gr",
                    c.getResources().getDrawable(R.drawable.home_ic_fthis),
                    s,
                    RootDirectoryfthis,
                    "https://fthis.gr/");
        }
        else if (s==Services.fireworktv)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryfireworktvShow,
                    "fireworktv",
                    c.getResources().getDrawable(R.drawable.home_ic_fireworktv),
                    s,
                    RootDirectoryfireworktv,
                    "https://fireworktv.com/");
        }
        else if (s==Services.coub)
        {
            servicesModel=new ServicesModel(
                    RootDirectorycoubShow,
                    "coub",
                    c.getResources().getDrawable(R.drawable.home_ic_coub),
                    s,
                    RootDirectorycoub,
                    "https://coub.com/");
        }
        else if (s==Services.rumble)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryrumbleShow,
                    "rumble",
                    c.getResources().getDrawable(R.drawable.home_ic_rumble),
                    s,
                    RootDirectoryrumble,
                    "https://rumble.com/");
        }
        else if (s==Services.fourshared)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryfoursharedShow,
                    "4shared",
                    c.getResources().getDrawable(R.drawable.home_ic_fourshared),
                    s,
                    RootDirectoryfourshared,
                    "https://4shared.com/");
        }
        else if (s==Services.ted)
        {
            servicesModel=new ServicesModel(
                    RootDirectorytedShow,
                    "Ted",
                    c.getResources().getDrawable(R.drawable.home_ic_ted),
                    s,
                    RootDirectoryted,
                    "https://ted.com/");
        }

        else if (s==Services.fouranim)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryfouranimeShow,
                    "4Anim",
                    c.getResources().getDrawable(R.drawable.home_ic_four_anime),
                    s,
                    RootDirectoryfouranime,
                    "");
        }
        else if (s==Services.pdisk)
        {
            servicesModel=new ServicesModel(
                    RootDirectorypdiskShow,
                    "PDisk",
                    c.getResources().getDrawable(R.drawable.home_ic_pdisk),
                    s,
                    RootDirectorypdisk,
                    "");
        }
        else if (s==Services.tiki)
        {
            servicesModel=new ServicesModel(
                    RootDirectorytikiShow,
                    "Tiki",
                    c.getResources().getDrawable(R.drawable.home_ic_tiki),
                    s,
                    RootDirectorytiki,
                    "");
        }
        else if (s==Services.Lomotif)
        {
            servicesModel=new ServicesModel(
                    RootDirectoryLomotifShow,
                    "Lomotif",
                    c.getResources().getDrawable(R.drawable.home_ic_lomotif),
                    s,
                    RootDirectoryLomotif,
                    "");
        }


        else
        {
            servicesModel=new ServicesModel();
        }
        return servicesModel;
    }

    public static void createFileFolder() {
        if (! RootDirectoryFacebookShow.exists()) {
            RootDirectoryFacebookShow.mkdirs();
        }
        if (!RootDirectoryInstaShow.exists()) {
            RootDirectoryInstaShow.mkdirs();
        }
        if (!RootDirectoryTikTokShow.exists()) {
            RootDirectoryTikTokShow.mkdirs();
        }
        if (!RootDirectoryTwitterShow.exists()) {
            RootDirectoryTwitterShow.mkdirs();
        }
        if (!RootDirectoryWhatsappShow.exists()) {
            RootDirectoryWhatsappShow.mkdirs();
        }
        if (!RootDirectoryLikeeShow.exists()) {
            RootDirectoryLikeeShow.mkdirs();
        }
        if (!RootDirectoryShareChatShow.exists()) {
            RootDirectoryShareChatShow.mkdirs();
        }
        if (!RootDirectoryRoposoShow.exists()) {
            RootDirectoryRoposoShow.mkdirs();
        }
        if (!RootDirectoryGDriveShow.exists()) {
            RootDirectoryGDriveShow.mkdirs();
        }
        if (!RootDirectorydailymotionShow.exists()) {
            RootDirectorydailymotionShow.mkdirs();
        }
        if (!RootDirectoryvimeoShow.exists()) {
            RootDirectoryvimeoShow.mkdirs();
        }
        if (!RootDirectoryChingariShow.exists()) {
            RootDirectoryChingariShow.mkdirs();
        }
        if (!RootDirectoryRizzleShow.exists()) {
            RootDirectoryRizzleShow.mkdirs();
        }
        if (!RootDirectoryZiliShow.exists()) {
            RootDirectoryZiliShow.mkdirs();
        }
        if (!RootDirectoryMitronShow.exists()) {
            RootDirectoryMitronShow.mkdirs();
        }
        if (!RootDirectoryTrellShow.exists()) {
            RootDirectoryTrellShow.mkdirs();
        }
        if (!RootDirectoryTrillerShow.exists()) {
            RootDirectoryTrillerShow.mkdirs();
        }
        if (!RootDirectoryMediafireShow.exists()) {
            RootDirectoryMediafireShow.mkdirs();
        }
        if (!RootDirectoryVKShow.exists()) {
            RootDirectoryVKShow.mkdirs();
        }
        if (!RootDirectorySnackVideoShow.exists()) {
            RootDirectorySnackVideoShow.mkdirs();
        }
        if (!RootDirectoryFunimateShow.exists()) {
            RootDirectoryFunimateShow.mkdirs();
        }
        if (!RootDirectoryByteShow.exists()) {
            RootDirectoryByteShow.mkdirs();
        }
        if (!RootDirectoryMXTakatakShow.exists()) {
            RootDirectoryMXTakatakShow.mkdirs();
        }
        if (!RootDirectoryFAIRTOKShow.exists()) {
            RootDirectoryFAIRTOKShow.mkdirs();
        }
        if (!RootDirectoryraaskShow.exists()) {
            RootDirectoryraaskShow.mkdirs();
        }
        if (!RootDirectoryOJOOShow.exists()) {
            RootDirectoryOJOOShow.mkdirs();
        }
        if (!RootDirectoryIMGURShow.exists()) {
            RootDirectoryIMGURShow.mkdirs();
        }

        if (!RootDirectoryTUMBLRShow.exists()) {
            RootDirectoryTUMBLRShow.mkdirs();
        }
        if (!RootDirectoryIMDBShow.exists()) {
            RootDirectoryIMDBShow.mkdirs();
        }
        if (!RootDirectoryPinterestShow.exists()) {
            RootDirectoryPinterestShow.mkdirs();
        }
        if (!RootDirectoryfansubsShow.exists()) {
            RootDirectoryfansubsShow.mkdirs();
        }
        if (!RootDirectoryespnShow.exists()) {
            RootDirectoryespnShow.mkdirs();
        }

        if (!RootDirectorynonegagShow.exists()) {
            RootDirectorynonegagShow.mkdirs();
        }
        if (!RootDirectorybilibiliShow.exists()) {
            RootDirectorybilibiliShow.mkdirs();
        }
        if (!RootDirectorybloggerShow.exists()) {
            RootDirectorybloggerShow.mkdirs();
        }

        if (!RootDirectoryizleseneShow.exists()) {
            RootDirectoryizleseneShow.mkdirs();
        }
        if (!RootDirectoryliveleakShow.exists()) {
            RootDirectoryliveleakShow.mkdirs();
        }
        if (!RootDirectorybitchuteShow.exists()) {
            RootDirectorybitchuteShow.mkdirs();
        }
        if (!RootDirectorylinkedinShow.exists()) {
            RootDirectorylinkedinShow.mkdirs();
        }
        if (!RootDirectorypopcornflixShow.exists()) {
            RootDirectorypopcornflixShow.mkdirs();
        }
        if (!RootDirectorymemeShow.exists()) {
            RootDirectorymemeShow.mkdirs();
        }

        if (!RootDirectorykickstarterShow.exists()) {
            RootDirectorykickstarterShow.mkdirs();
        }
        if (!RootDirectoryvliveShow.exists()) {
            RootDirectoryvliveShow.mkdirs();
        }
        if (!RootDirectoryvlipsyShow.exists()) {
            RootDirectoryvlipsyShow.mkdirs();
        }
        if (!RootDirectoryvidlitShow.exists()) {
            RootDirectoryvidlitShow.mkdirs();
        }
        if (!RootDirectorygloriatvShow.exists()) {
            RootDirectorygloriatvShow.mkdirs();
        }
        if (!RootDirectoryaparatShow.exists()) {
            RootDirectoryaparatShow.mkdirs();
        }
        if (!RootDirectoryaparatShow.exists()) {
            RootDirectoryaparatShow.mkdirs();
        }
        if (!RootDirectoryonetvruShow.exists()) {
            RootDirectoryonetvruShow.mkdirs();
        }
        if (!RootDirectoryallocineShow.exists()) {
            RootDirectoryallocineShow.mkdirs();
        }
        if (!RootDirectoryveerShow.exists()) {
            RootDirectoryveerShow.mkdirs();
        }
        if (!RootDirectorykooappShow.exists()) {
            RootDirectorykooappShow.mkdirs();
        }
        if (!RootDirectorystreamableShow.exists()) {
            RootDirectorystreamableShow.mkdirs();
        }
        if (!RootDirectorygfycatShow.exists()) {
            RootDirectorygfycatShow.mkdirs();
        }
        if (!RootDirectoryfthisShow.exists()) {
            RootDirectoryfthisShow.mkdirs();
        }
        if (!RootDirectoryfireworktvShow.exists()) {
            RootDirectoryfireworktvShow.mkdirs();
        }
        if (!RootDirectorycoubShow.exists()) {
            RootDirectorycoubShow.mkdirs();
        }
        if (!RootDirectoryrumbleShow.exists()) {
            RootDirectoryrumbleShow.mkdirs();
        }
        if (!RootDirectoryfoursharedShow.exists()) {
            RootDirectoryfoursharedShow.mkdirs();
        }
        if (!RootDirectoryfouranimeShow.exists()) {
            RootDirectoryfouranimeShow.mkdirs();
        }
        if (!RootDirectorypdiskShow.exists()) {
            RootDirectorypdiskShow.mkdirs();
        }
        if (!RootDirectorytikiShow.exists()) {
            RootDirectorytikiShow.mkdirs();
        }
        if (!RootDirectoryLomotifShow.exists()) {
            RootDirectoryLomotifShow.mkdirs();
        }

    }

    public static String SanitiseURL(String URL){
        return URL.trim();
    }

    public static long Xdownload(String Dir, Context context2, String FineName, XModel xModel)
    {
        if (!new File(Dir).exists()) {
            new File(Dir).mkdir();
        }
        Uri downloadUri = Uri.parse(xModel.getUrl());
        String str4 = Environment.DIRECTORY_DOWNLOADS;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Dir);
        sb2.append(FineName);
        Request  mRequest = new DownloadManager.Request(downloadUri);
        mRequest.setDestinationInExternalPublicDir(str4, sb2.toString());
        if (xModel.getCookie()!=null){
            mRequest.addRequestHeader("cookie", xModel.getCookie());
        }
        mRequest.setMimeType("video/*");
        mRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        return ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(mRequest);
    }

    public static long startDownload(String str, String str2, Context context2, String str3)
    {
        Request request = new Request(Uri.parse(str));
        request.setAllowedNetworkTypes(3);
        request.setNotificationVisibility(1);
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append("");
        request.setTitle(sb.toString());
        String str4 = Environment.DIRECTORY_DOWNLOADS;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(str3);
        request.setDestinationInExternalPublicDir(str4, sb2.toString());
        return ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
    }

}
