package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.OffersPojo;
import test.util.TestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OffersTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private Offers offers;

    @BeforeClass
    @Parameters({"offerId1", "offerId2", "name1", "name2", "depId1", "depId2"})
    public void setUpClass(long offerId1, long offerId2, String name1, String name2, String depId1, String depId2) {
        assertFalse(offerId1 < 0L, "Offer id is less than 0!");
        assertFalse(offerId2 < 0L, "Offer id is less than 0!");
        assertNotNull(name1, "Offer name is null!");
        assertNotNull(name2, "Offer name is null!");
        assertNotNull(depId1, "Department id is null!");
        assertNotNull(depId2, "Department id is null!");
        assertFalse(name1.isEmpty(), "Department id is empty!");
        assertFalse(name2.isEmpty(), "Department id is empty!");
        assertFalse(depId1.isEmpty(), "Department id is empty!");
        assertFalse(depId2.isEmpty(), "Department id is empty!");

        List<Offer> offersList = new ArrayList<>() {{
            add(new OfferWithDateOfferImpl(
                    new OfferWithDepartmentsImpl(
                            new OfferWithNameImpl(
                                    new OfferSimpleImpl(offerId1),
                                    name1
                            ),
                            new ArrayList<>() {{
                                add(new DepartmentWithIdImpl(depId1));
                                add(new DepartmentWithIdImpl(depId2));
                            }}
                    ),
                    new DateOfferFinishesAtImpl(
                            new DateOfferStartsAtImpl(LocalDateTime.now()),
                            LocalDateTime.now().plusDays(30)
                    )
            ));
            add(new OfferWithDateOfferImpl(
                    new OfferWithDepartmentsImpl(
                            new OfferWithNameImpl(
                                    new OfferSimpleImpl(offerId2),
                                    name2
                            ),
                            new ArrayList<>() {{
                                add(new DepartmentWithIdImpl(depId1));
                            }}
                    ),
                    new DateOfferFinishesAtImpl(
                            new DateOfferStartsAtImpl(LocalDateTime.now()),
                            LocalDateTime.now().plusDays(60)
                    )
            ));
        }};

        offers = new OffersImpl(offersList);
        assertNotNull(offers, "Offers is null!");

    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(OffersImpl.class, "formForSend()");

        OffersPojo offersPojo = (OffersPojo) offers.formForSend();
        assertNotNull(offersPojo, "OffersPojo is null!");
        System.out.println(mapper.writeValueAsString(offersPojo));

        testEnd(OffersImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
