/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 *
 * @author andrey
 */
@Entity
public class Track extends Model
{
    public Blob file;
    public String artist;
    public String song;
    public String length;
}
