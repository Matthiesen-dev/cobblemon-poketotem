package dev.matthiesen.common.cobblemon_poketotem.util;

import dev.matthiesen.common.cobblemon_poketotem.Constants;
import dev.matthiesen.common.matthiesen_lib_api.MatthiesenLibApi;
import dev.matthiesen.common.matthiesen_lib_api.core.MatthiesenLibApiMetricsManager;
import dev.matthiesen.common.matthiesen_lib_api.core.metric.UniversalMetricContext;
import dev.matthiesen.libs.faststats.ErrorTracker;

public final class MetricManager {
    public static final ErrorTracker ERROR_TRACKER = MatthiesenLibApiMetricsManager.getErrorTracker();
    @SuppressWarnings("unused")
    private static final UniversalMetricContext METRIC_CONTEXT = MatthiesenLibApiMetricsManager.makeErrorMetricsContext(
            Constants.MOD_ID,
            Constants.METRICS_TOKEN,
            ERROR_TRACKER
    );

    public static void init() {
        MatthiesenLibApi.registerModToApiMetrics(Constants.MOD_ID);
    }
}
