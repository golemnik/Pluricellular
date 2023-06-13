package com.golem.filterGreaterThenVenue;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.util.ArrayList;
import java.util.List;

public class FilterGreaterVenueTCellFactory extends AbstractTCellFactory {
    public FilterGreaterVenueTCellFactory() {
        super (new Signature(
                "filter_greater_than_venue",
                "remove all tickets greater then object was inputted during this command.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of(
                        "(filter_greater_than_venue",
                        SignatureRegex._string, // v name
                        SignatureRegex.CAP, // v cap
                        SignatureRegex.VTYPE, //v type
                        SignatureRegex._string // v addr
                )),
                new ArrayList<>(List.of(
                        "",
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
                        "input does not meet the requirements, try again:"
                        ))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        FilterGreaterVenueTCommandCell cell = new FilterGreaterVenueTCommandCell();
        cell.setCollection(getCollection());
        return cell.useSignature(signature);
    }
}
