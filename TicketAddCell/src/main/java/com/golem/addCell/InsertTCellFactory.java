package com.golem.addCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.util.ArrayList;
import java.util.List;

public class InsertTCellFactory extends AbstractTCellFactory {
    public InsertTCellFactory() {
        super (new Signature(
                "insert",
                "add ticket to the collection.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of(
                        "^" + "insert( " + SignatureRegex.ID +")?" + "$",
                        "^" + SignatureRegex._string + "$", //t name
                        "^" + SignatureRegex.PRICE + "$", //t price
                        "^" + SignatureRegex._string + "$", // t comm
                        "^" + SignatureRegex.TTYPE + "$", // t type
                        "^" + SignatureRegex.COORD_X + "$", // t x
                        "^" + SignatureRegex.COORD_Y + "$", //t y
                        "^" + SignatureRegex._string + "$", // v name
                        "^" + SignatureRegex.CAP + "$", // v cap
                        "^" + SignatureRegex.VTYPE + "$", //v type
                        "^" + SignatureRegex._string + "$" // v addr
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
        InsertTCommandCell cell = new InsertTCommandCell();
        cell.setManager(getManager());
        return cell.useSignature(signature);
    }
}
