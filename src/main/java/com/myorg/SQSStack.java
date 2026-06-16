package com.myorg;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.CfnOutputProps;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

public class SQSStack extends Stack {

    public SQSStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SQSStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        String queueName = (String) this.getNode().tryGetContext("queueName");
        Number queueRetentionDays = (Number) this.getNode().tryGetContext("queueRetentionDays");

        Queue queue = Queue.Builder.create(this, "MyCdkQueue")
                .queueName(queueName)
                .retentionPeriod(Duration.days(queueRetentionDays.intValue()))
                .visibilityTimeout(Duration.seconds(500))
                .build();

        new CfnOutput(this, "SQSQueueARN", CfnOutputProps.builder()
                .value(queue.getQueueArn())
                .exportName("MySQSQueueARN")
                .build());
    }
}
