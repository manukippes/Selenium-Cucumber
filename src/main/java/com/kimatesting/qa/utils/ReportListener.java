package com.kimatesting.qa.utils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.kimatesting.qa.pages.BasePage;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import java.util.HashMap;
import java.util.Map;


public class ReportListener implements ConcurrentEventListener {

    private static ExtentReports extent;
    private static ExtentTest featureTest;
    private static String scenarioName;
    private static Map<String, ExtentTest> scenarioTests = new HashMap<>();


    public ReportListener() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setAnalysisStrategy(AnalysisStrategy.BDD);
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::onTestStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
        eventPublisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    private synchronized void onTestStarted(TestCaseStarted event) {
        TestCase testCase = event.getTestCase();
        String featureName = testCase.getUri().getPath().replace(".feature", "").split("/features/")[1];
        scenarioName = testCase.getName();

        if (featureTest == null || !featureName.equals(featureTest.getModel().getName())) {
            featureTest = extent.createTest(Feature.class,featureName);
        }

        ExtentTest scenarioTest = featureTest.createNode(Scenario.class,scenarioName);
        scenarioTests.put(scenarioName, scenarioTest);
    }

    private synchronized void onStepFinished(TestStepFinished event) {

        if (! event.getTestStep().getClass().getName().contains("HookTestStep")) {
            String scenarioName = event.getTestCase().getName();
            ExtentTest scenarioTest = scenarioTests.get(scenarioName);

            if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
                String stepText = testStep.getStep().getText();
                String keyword = testStep.getStep().getKeyword();

                Result result = event.getResult();
                ExtentTest scenarioStep = scenarioTest.createNode(keyword+" "+stepText);

                switch (result.getStatus()) {
                    case FAILED:
                        scenarioTest.fail("FAILD");
                        scenarioStep.fail(result.getError().fillInStackTrace(), MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.takeScreenshot(),"[ERROR-SCREEN]").build());
                        break;
                    case PENDING:
                    case UNDEFINED:
                    case AMBIGUOUS:
                    case UNUSED:
                        scenarioStep.fail("Step " + result.getStatus().toString().toLowerCase());
                        scenarioTest.fail("Step " + result.getStatus().toString().toLowerCase());
                        break;
                    case SKIPPED:
                        scenarioStep.skip("");
                        scenarioTest.skip("");
                        break;
                    default:
                        scenarioStep.pass("");
                        scenarioTest.pass("");
                        break;
                }

            }
        }
    }

    private synchronized void onTestRunFinished(TestRunFinished event) {
        extent.flush();
    }

}