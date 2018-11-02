package com.albertobay.paymentapp.presenter;

import com.albertobay.paymentapp.data.model.PaymentMethod;
import com.albertobay.paymentapp.interactor.CreditCardInteractor;

import java.util.List;

/**
 * Created by Alberto Bay on 31,October,2018
 */
public class CreditCardPresenter extends Presenter<CreditCardPresenter.View> {

    private CreditCardInteractor interactor;

    public CreditCardPresenter(CreditCardInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void onSearchPayments(String string) {
        getView().showLoading();
        interactor.loadPaymentMethods(string).subscribe(payments -> {
            if (!payments.isEmpty() && payments.size() > 0) {
                getView().hideLoading();
                getView().renderPayments(payments);
            } else {
                getView().showPaymentsNotFoundMessage();
            }
        }, Throwable::printStackTrace);
    }

    public void launchBankDetail(PaymentMethod paymentMethod, int position) {
        getView().launchSelectBankDetail(paymentMethod, position);
    }


    public interface View extends Presenter.View {

        void showLoading();

        void hideLoading();

        void showPaymentsNotFoundMessage();

        void renderPayments(List<PaymentMethod> payment);

        void launchSelectBankDetail(PaymentMethod paymentMethod, int position);

    }
}