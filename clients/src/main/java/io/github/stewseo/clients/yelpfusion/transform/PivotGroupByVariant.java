package io.github.stewseo.clients.yelpfusion.transform;

public interface PivotGroupByVariant {

    PivotGroupBy.Kind _pivotGroupByKind();

    default PivotGroupBy _toPivotGroupBy() {
        return new PivotGroupBy(this);
    }

}
