package me.lphix.essentialsl.sql;

import me.lphix.essentialsl.EssentialsL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private EssentialsL plugin;
    public SQLGetter(EssentialsL plugin){
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;
        try{
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS teststat "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), POINTS INT(100), PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO teststat"
                        + " (NAME,UUID) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM teststat WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            return results.next();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addPoints(UUID uuid, int stat){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE teststat SET POINTS =? WHERE UUID=?");
            ps.setInt(1, (getPoints(uuid) + stat));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getPoints(UUID uuid){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM teststat WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next()){
                points = rs.getInt("POINTS");
                return points;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
