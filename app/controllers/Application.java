package controllers;

import java.io.IOException;
import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
import org.blinkenlights.jid3.*;
import org.blinkenlights.jid3.v1.*;
import org.blinkenlights.jid3.v2.*;

public class Application extends Controller {

    public static void index() 
    {
        List<Track> tracks = Track.all().fetch(10);
        render(tracks);
        //render();
    }

    public static void search(String q)
    {
        System.out.println("query= "+ q);
        List<Track> tracks = Track.find("byArtistLike", "%"+q+"%").fetch();
        //List<Track> tracks = Track.findAll();
        render(tracks);
    }

    public static void uploadTrack(Track track)
    {
        MediaFile oMediaFile = new MP3File(track.file.getFile());
        try
        {
            ID3V2Tag tag = oMediaFile.getID3V2Tag();
            if (tag != null && !tag.getArtist().trim().equals("")) {
                track.artist = tag.getArtist();
                track.song = tag.getTitle();
            } 
            else
            {
                ID3V1Tag tag1 = oMediaFile.getID3V1Tag();
                if (tag != null)
                {
                    track.artist = tag.getArtist();
                    track.song = tag.getTitle();
                }
            }
        } 
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        track.save();
        index();
    }

    public static void getTrack(long id) {
        Track track = Track.findById(id);
        render(track);
    }

    public static void getLink(long id) throws IOException {
        Track track = Track.findById(id);
        response.setContentTypeIfNotSet(track.file.type());
        renderBinary(track.file.get());
    }

    public static void show(long id) throws IOException {
        Track t = Track.findById(id);
        render(t);
    }


}
