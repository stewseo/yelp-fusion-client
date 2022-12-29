package io.github.stewseo.client.yelpfusion.transform;

public interface PivotGroupByVariant {

    PivotGroupBy.Kind _pivotGroupByKind();

    default PivotGroupBy _toPivotGroupBy() {
        return new PivotGroupBy(this);
    }

}
