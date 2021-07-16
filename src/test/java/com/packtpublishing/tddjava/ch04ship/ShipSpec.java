package com.packtpublishing.tddjava.ch04ship;

import com.beust.jcommander.internal.Lists;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        planet = new Planet(max);

        /*设置行星的陆地位置，无法前行*/
        Point obstacle = new Point(22, 14);
        List<Point> obstacleList = Lists.newArrayList();
        obstacleList.add(obstacle);
        planet.setObstacles(obstacleList);

//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);
        assertEquals(ship.getLocation(), location);
    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {
        Location expected = location.copy();
        expected.forward();
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenBackward() {
        Location expected = location.copy();
        expected.backward();
        ship.moveBackward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnLeftThenLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.turnLeft();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnRightThenRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.turnRight();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsFThenForward() {
        Location expected = location.copy();
        expected.forward();
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsBThenBackward() {
        Location expected = location.copy();
        expected.backward();
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.receiveCommands("l");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsRThenTurnRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.receiveCommands("r");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        Location expected = location.copy();
        expected.turnRight();
        expected.forward();
        expected.turnLeft();
        expected.backward();
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation(), expected);
    }
    /** 当实例化时，行星被存储 */
    public void whenInstantiatedThenPlanetIsStored() {
//        Point max = new Point(50, 50);
//        Planet planet = new Planet(max);
//        ship = new Ship(location, planet);
        assertEquals(ship.getPlanet(), planet);
    }
    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(planet.getMax().getX());
        ship.receiveCommands("f");
        assertEquals(location.getX(), 1);
    }
    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(1);
        ship.receiveCommands("b");
        System.out.println(location.getPoint());
        System.out.println("planet.getMax().getX():" + planet.getMax().getX());
        assertEquals(location.getX(), planet.getMax().getX());
    }

    /**虽然地球表面的很大一部分(大约70%)都被水覆盖，但还有一些大洲和岛屿，
     * 它们对遥控军舰来说就是障碍。我们需要能够检测接着移动军舰是否会撞上这些障碍，
     * 如果会，就应放弃这样的移动，让军舰留在原地并报告将遇到的障碍。
     每次移动前都进行障碍检测。如果执行指定的命令将遇到障碍，军舰应放弃移动，留在原地并报告遇到的障碍。
     这个规范及其实现与前面所做的很像，这些工作留给你自己去完成。
     下面几个小提示可能会对你有所帮助：
     Planet类有一个构造函数将障碍列表作为参数。每个障碍都是一个Point实例。
     方法Location.forward和Location.backward都有将障碍列表作为参数的重载版本，它们在移动成功时返回true，
     在移动失败时返回false。你可使用这个返回的布尔值创建方法Ship.receiveCommands所需的状态报告。
     方法receiveCommands应返回一个字符串，以指出每个命令的状态。例如，你可使用O表示OK，
     用X表示失败(例如，OOXO表示OK、OK、失败和OK)。*/
    public void whenZhangAiThenStop(){

        System.out.println("已有障碍物：" + planet.getObstacles());
        // 北朝向，初始坐标（21,13）, 障碍坐标(22,14)
        location.setDirection(Direction.NORTH);

        System.out.println("遥控飞船初始坐标：" + location.getPoint());

        Location copy = location.copy();
        copy.forward();
        copy.turnRight();
        System.out.println("" + copy.getPoint() + "朝向： " + copy.getDirection());
        String result = ship.receiveCommands("frf");
        assertEquals(result, "OOX");
    }
}
