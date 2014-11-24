package model.distribution;

import exception.UnsupportedException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Estimation of and sampling from normal distributions.
 */
public class NormalDistribution implements Distribution {

    /** Mean of the distribution */
    private double mean;

    /** Scale of the distribution */
    private double stddev;

    /** Internal distribution which will be fit and sampled from */
    private org.apache.commons.math3.distribution.NormalDistribution normalDistr;

    /**
     * Creates a normal distribution without predefined parameters.
     * Parameters must be fit with a vector of samples.
     */
    public NormalDistribution() {

    }

    /**
     * Creates a normal distribution with predefined parameters.
     * @param mean The location of the distribution.
     * @param stddev The scale of the distribution.
     */
    public NormalDistribution(double mean, double stddev)  {
        this.setParameters(mean, stddev);
    }

    /**
     * Unsupported -- univariate normal distributions can only be fit from a vector of samples.
     * @param X covariates Not used, unsupported
     * @param y outcome Not used, unsupported
     */
    @Override
    public void fit(RealMatrix X, RealVector y) {
        throw new UnsupportedException("Normal distributions do not support regression-style fitting.");
    }

    /**
     * Fits the distribution by estimating the sample mean and standard deviation
     * @param y outcome The samples to use when estimating mean and standard deviation.
     */
    @Override
    public void fit(RealVector y) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for(int i = 0; i < y.getDimension(); i++) {
            stats.addValue(y.getEntry(i));
        }

        this.setParameters(stats.getMean(), stats.getStandardDeviation());
    }

    /**
     * Establishes parameters for this instances
     * @param mean The mean of the distribution
     * @param stddev The standard deviation of the distribbution
     */
    public void setParameters(double mean, double stddev) {
        this.mean = mean;
        this.stddev = stddev;
        this.normalDistr = new org.apache.commons.math3.distribution.NormalDistribution(mean, stddev);
    }

    /**
     * Samples <code>numberOfSamples</code> from the normal distribution
     * @param numberOfSamples the number of samples to be generated
     * @return A vector of sampled valued
     */
    @Override
    public RealVector sample(int numberOfSamples)  {
        return new ArrayRealVector(this.normalDistr.sample(numberOfSamples));
    }

    /**
     * Unsupported, cannot perform regression-style fitting of a normal distribution
     * @param X Unsupported, would be a vector of covariates
     * @throws exception.UnsupportedException
     * @return Nothing
     */
    @Override
    public RealVector sample(RealMatrix X) {
       throw new UnsupportedException("Normal distribution do not support regression-style sampling.");
    }
}
