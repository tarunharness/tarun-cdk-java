package com.myorg;

import software.amazon.awscdk.App;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new S3Stack(app, "S3Stack");
        new SQSStack(app, "SQSStack");
        // new EC2Stack(app, "EC2Stack");

        app.synth();
    }
}
