package checkers.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DashboardTest {
    Dashboard dashboard;

    @BeforeEach
    public void createDashboard() {
        dashboard = new Dashboard();
    }

    @Test
    public void whiteTokenIsInFirstPositionRequireNotError() {
        Coordinate origin = new Coordinate(0, 0);
        Token token = dashboard.getTokenInPosition(origin);
        assertNotNull(token);
        assertEquals(token.getColor(), Color.WHITE);
    }

    @Test
    public void nonWhiteTokenIsInLastPositionRequireNotError() {
        Coordinate origin = new Coordinate(0, 7);
        assertNull(dashboard.getTokenInPosition(origin));
    }

    @Test
    public void blackTokenIsInFirstPositionRequireNotError() {
        Coordinate origin = new Coordinate(7, 7);
        Token token = dashboard.getTokenInPosition(origin);
        assertNotNull(token);
        assertEquals(token.getColor(), Color.BLACK);
    }

    @Test
    public void nonBlackTokenIsInLastPositionRequireNotError() {
        Coordinate origin = new Coordinate(7, 0);
        assertNull(dashboard.getTokenInPosition(origin));
    }

}