package com.golem.removeGreater;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterTCellFactory extends AbstractTCellFactory {
    public RemoveGreaterTCellFactory() {
        super (new Signature(
                "remove_greater",
                "remove all tickets greater then object was inputted during this command    .",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of(
                        "(remove_greater -?[1-9]\\d{0,8}|0|214748364[0-7]$?)",
                        "^.*", //t name
                        "[-+]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][-+]?[0-9]+)?$?", //t price
                        "^.*", // t comm
                        "(VIP)|(USUAL)|(BUDGETARY)|(CHEAP)", // t type
                        "^(-?[1-9]\\d{0,18}|0|9(\\d{0,17})|-[1-8]\\d{0,18}|-9(\\d{0,17}))$?", // t x
                        "((-[1-9]\\\\d{0,18}|0|9(\\\\d{0,17})|-[1-8]\\\\d{0,18}|-9(\\\\d{0,17}))|[1-8][1-9][1-9]|900)", //t y
                        "^.*$?", // v name
                        "^([1-9]\\d{0,18}|0)$?", // v cap
                        "(BAR)|(LOFT)|(OPEN_AREA)|(THEATRE)|(MALL)$?", //v type
                        "^.*$?" // v addr
                        )),
                new ArrayList<>(List.of(
                        "",
                        "Type ticket name. It can't be null or empty:",
                        "Type ticket price. It must be greater than 0, but could be double:",
                        "Type comment to this ticket. It can be null or empty:",
                        """
                        Choose type for this ticket. It can be one from the following list:
                        VIP
                        USUAL
                        BUDGETARY
                        CHEAP
                        Write chosen type:""",
                        "Type ticket x coordinate. It can't be null, but could be long:",
                        "Type ticket y coordinate. It can't be null or greater than 900, but could be long:",
                        "Type ticket's venue name. It can't be null or empty:",
                        "Type ticket's venue capacity. It must be greater than 0 and not null, but could be long:",
                        """
                        Choose type for this ticket's venue. It can be one from the following list:
                        BAR
                        LOFT
                        OPEN_AREA
                        THEATRE
                        MALL
                        Write chosen type:""",
                        "Type ticket's venue address. It can't be null:"
                )),
                new ArrayList<>(List.of(
                        "unsupported arguments",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:",
                        "input does not meet the requirements, try again:"
                        ))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        RemoveGreaterTCommandCell cell = new RemoveGreaterTCommandCell();
        cell.setCollection(getCollection());
        return cell.useSignature(signature);
    }
}
