package com.myorg;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.CfnOutputProps;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;
import software.constructs.Construct;

public class S3Stack extends Stack {

    public S3Stack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public S3Stack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Bucket bucket = Bucket.Builder.create(this, "MyCdkBucket")
                .bucketName("sunny-cdk-test-bucket-04")
                .versioned(true)
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
//                .encryption(BucketEncryption.S3_MANAGED)
                .build();

        new CfnOutput(this, "S3BucketARN", CfnOutputProps.builder()
                .value(bucket.getBucketArn())
                .exportName("MyS3BucketARN")
                .build());
    }
}
