/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.BoundaryCours;
import boundary.BoundaryVideo;
import entity.Cours;
import entity.Video;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PresentationVideo implements Serializable {

    @Inject
    BoundaryVideo boundaryVideo;
    private Video video;
    private List<Video> listeVideo = new ArrayList<>();

    @PostConstruct
    public void onInit() {
        this.video = new Video();
    }

    public String doSupprimer(long vId) {
        this.setVideo(boundaryVideo.find(vId));
        boundaryVideo.Supprimer(video);
        return "modifCours.xhtml?includeViewParams=true";
    }

    public BoundaryVideo getBoundaryVideo() {
        return boundaryVideo;
    }

    public void setBoundaryVideo(BoundaryVideo boundaryVideo) {
        this.boundaryVideo = boundaryVideo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<Video> getListeVideo() {
        return listeVideo;
    }

    public void setListeVideo(List<Video> listeVideo) {
        this.listeVideo = listeVideo;
    }
}
