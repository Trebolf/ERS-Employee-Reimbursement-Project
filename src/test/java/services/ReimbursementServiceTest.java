package services;

import models.subclass.ReimbursementListAll;
import models.subclass.ReimbursementListPersonal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.ReimbursementDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {
    private ReimbursementService reimbursementService;
    private ReimbursementDAO reimbursementDAO = Mockito.mock(ReimbursementDAO.class);

    public ReimbursementServiceTest() {
        reimbursementService = new ReimbursementService(reimbursementDAO);
    }

    @Test
    void getAllReimbursement() {
        List<ReimbursementListAll> expectedOutput = new ArrayList<>();
        expectedOutput.add(new ReimbursementListAll(1, 1, "first", "last",
                "type", 42.42, "desc", "status",
                null, null));
        expectedOutput.add(new ReimbursementListAll(2, 2, "first", "last",
                "type", 69.69, "desc", "status",
                null, null));
        Mockito.when(reimbursementDAO.getAllReimbursement()).thenReturn(expectedOutput);

        List<ReimbursementListAll> actualOutput = reimbursementService.getAllReimbursement();

        assertEquals(expectedOutput,actualOutput);
        System.out.println("Result of getting all reimbursements: " + expectedOutput);
    }

    @Test
    void getAllReimbursementGivenUsername() {
        String username = "mockuser";
        List<ReimbursementListPersonal> expectedOutput = new ArrayList<>();
        expectedOutput.add(new ReimbursementListPersonal(1, "type", 42.42,
                "status", "desc", null, null));
        expectedOutput.add(new ReimbursementListPersonal(2, "type", 69.69,
                "status", "desc", null, null));
        Mockito.when(reimbursementDAO.getAllReimbursementGivenUsername(username)).thenReturn(expectedOutput);

        List<ReimbursementListPersonal> actualOutput = reimbursementService.getAllReimbursementGivenUsername(username);

        assertEquals(expectedOutput,actualOutput);
        System.out.println("Result of getting reimbursements for username: " + username + expectedOutput);
    }

    @Test
    void approveReimbursement() {
        Integer listId = 2;

        reimbursementService.approveReimbursement(listId);

        Mockito.verify(reimbursementDAO, Mockito.times(1)).approveReimbursement(listId);
    }

    @Test
    void denyReimbursement() {
        Integer listId = 2;

        reimbursementService.denyReimbursement(listId);

        Mockito.verify(reimbursementDAO, Mockito.times(1)).denyReimbursement(listId);
    }

    @Test
    void createReimbursementGivenAuthorId() {
        ReimbursementListPersonal reimbToPass = new ReimbursementListPersonal(42.42,"desc",8,1);

        reimbursementService.createReimbursementGivenAuthorId(reimbToPass);

        Mockito.verify(reimbursementDAO, Mockito.times(1)).createReimbursementGivenAuthorId(reimbToPass);
    }

    @Test
    void deleteReimbursement() {
        Integer listId = 2;

        reimbursementService.deleteReimbursement(listId);

        Mockito.verify(reimbursementDAO, Mockito.times(1)).deleteReimbursement(listId);
    }
}