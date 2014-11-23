package model;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * Created by darbour on 11/19/14.
 */
public interface Distribution{
    /**
     * Fit the distribution to data
     * @param X covariates
     * @param y outcome
     */
    public void fit(RealMatrix X, RealVector y);

    /**
     * Fit a marginal distribution to data
     * @param y outcome
     */
    public void fit(RealVector y);

    /**
     * Sample from the marginal distribution
     * @param numberOfSamples the number of samples to be generated
     * @return a RealVector containing the samples
     */
    public RealVector sample(int numberOfSamples);

    /**
     * Sample from the conditional distribution
     * @param X given values
     * @return a RealVector of size nrow(x)
     */
    public RealVector sample(RealMatrix X);
}
