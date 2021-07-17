package bo.custom;

import dto.*;
import entity.SampleUnit;
import javafx.collections.ObservableList;
import view.tm.RequestTableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceRequestBo {
   public boolean placeRequest(PaymentDTO paymentDTO, RequestDTO requestDTO, SampleUnitDTO sampleUnitDTO, ObservableList<RequestTableTM> detaillist)throws Exception;
}
