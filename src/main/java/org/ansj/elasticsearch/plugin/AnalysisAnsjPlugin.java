package org.ansj.elasticsearch.plugin;

import org.ansj.elasticsearch.index.AnsjAnalysisBinderProcessor;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

public class AnalysisAnsjPlugin extends AbstractPlugin {

    private static final String PLUGIN_NAME = "analysis-ansj-advance";
    private static final String PLUGIN_DESCRIPTION = "ANSJ analysis advance";
    @Override public String name() {
        return PLUGIN_NAME;
    }


    @Override public String description() {
        return PLUGIN_DESCRIPTION;
    }


    @Override public void processModule(Module module) {
        if (module instanceof AnalysisModule) {
            AnalysisModule analysisModule = (AnalysisModule) module;
            analysisModule.addProcessor(new AnsjAnalysisBinderProcessor());
        }
    }
    
}