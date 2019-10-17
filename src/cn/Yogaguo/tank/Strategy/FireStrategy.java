package cn.Yogaguo.tank.Strategy;

import cn.Yogaguo.tank.Player;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
       void fire(Player p);
}
