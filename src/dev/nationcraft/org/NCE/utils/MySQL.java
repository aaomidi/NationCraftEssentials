package dev.nationcraft.org.NCE.utils;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import dev.nationcraft.org.NCE.NCE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.bukkit.Bukkit;

public class MySQL {

    private NCE _plugin;
    public static Connection connection = null;
    String connector = "jdbc:mysql://";

    public MySQL(NCE plugin) {
        _plugin = plugin;
    }

    public Boolean connect(String host, String db, String username, String password) {
        connector += host + "/" + db;
        try {
            Connection con = DriverManager.getConnection(connector, username, password);
            connection = con;
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean ticketPlayer(String player, String support) {
        PreparedStatement pst = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(cal.getTime());
        try {
            pst = (PreparedStatement) connection.prepareStatement("INSERT INTO `tickets` (`servername`, `playername`, `ticket`, `datetime`) VALUES(?, ?, ?, ?)");
            pst.setString(1, Enabler.servername);
            pst.setString(2, player);
            pst.setString(3, support);
            pst.setString(4, time);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.print(ex);
            NCEChat.LogWarning("SQL Aint Working! (1)");
        }
        return false;
    }

    public static boolean checkTickets(String player) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = (Statement) connection.createStatement();
            if (stmt.execute("SELECT * FROM `tickets` WHERE `playername`='" + player + "' AND `coo`=1")) {
                rs = stmt.getResultSet();
                if (!rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
            NCEChat.LogWarning("SQL Aint Working! (2)");
        }
        return false;
    }

    public static boolean sendTicketsAdmin(String player, int imp) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = (Statement) connection.createStatement();
            if (stmt.execute("SELECT * FROM `tickets` WHERE `coo`=1 AND `imp`=" + imp)) {
                rs = stmt.getResultSet();
                if (!rs.next()) {
                } else {
                    int counter = 0;
                    rs.getFetchSize();
                    while (rs.next()) {
                        counter++;
                       
                    }
                    NCEChat.sendMessage(Bukkit.getPlayer(player), "&6There are currently &c" + counter + " &6tickets! Use &a/ticket show &6to review them!");

                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
            NCEChat.LogWarning("SQL Aint Working! (2)");
        }
        return false;
    }
}
