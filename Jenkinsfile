@Library('gbif-common-jenkins-pipelines') _

pipeline {
  agent any
  tools {
    maven 'Maven 3.9.9'
    jdk 'OpenJDK17'
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
    skipStagesAfterUnstable()
    timestamps()
    disableConcurrentBuilds()
  }
  triggers {
    snapshotDependencies()
  }
  parameters {
    separator(name: "release_separator", sectionHeader: "Release Main Project Parameters")
    booleanParam(name: 'RELEASE', defaultValue: false, description: 'Do a Maven release')
    string(name: 'RELEASE_VERSION', defaultValue: '', description: 'Release version (optional)')
    string(name: 'DEVELOPMENT_VERSION', defaultValue: '', description: 'Development version (optional)')
    booleanParam(name: 'DRY_RUN_RELEASE', defaultValue: false, description: 'Dry Run Maven release')
  }
  environment {
    JETTY_PORT = utils.getPort()
  }
  stages {

    stage('Setup') {
      steps {
        script {
          // Branch-qualified version is workspace-only and is never committed.
          // Example: feature/foo -> 0.0.7-FEATURE-FOO-SNAPSHOT
          if (env.BRANCH_NAME != 'dev' && env.BRANCH_NAME != 'master' && !params.RELEASE) {
            def suffix = env.BRANCH_NAME.replaceAll('[^a-zA-Z0-9.-]', '-').toUpperCase()

            if (!suffix?.trim()) {
              error("Could not derive a valid version suffix from branch '${env.BRANCH_NAME}'")
            }

            sh """
              set -e
              BASE_VERSION=\$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout | sed 's/-SNAPSHOT//')

              if [ -z "\${BASE_VERSION}" ]; then
                echo >&2 "ERROR: Maven returned an empty project version while preparing branch '${env.BRANCH_NAME}'."
                exit 1
              fi

              echo "Using feature branch Maven version: \${BASE_VERSION}-${suffix}-SNAPSHOT"

              mvn versions:set \
                -DnewVersion=\${BASE_VERSION}-${suffix}-SNAPSHOT \
                -DgenerateBackupPoms=false \
                -DprocessAllModules=true
            """
          }

          echo "Release build: ${params.RELEASE}"
        }
      }
    }

    stage('Maven Spotless') {
      steps {
        withMaven(globalMavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.GlobalMavenSettingsConfig1387378707709',
                  mavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig1396361652540',
                  traceability: true) {
          sh 'mvn spotless:check'
        }
      }
    }

    stage('Maven build') {
       when {
        allOf {
          not { expression { params.RELEASE } };
        }
      }
      steps {
        withMaven(
            globalMavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.GlobalMavenSettingsConfig1387378707709',
            mavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig1396361652540',
            traceability: true) {
                sh '''
                   mvn -B -U clean package dependency:analyze
                  '''
              }
      }
    }
    stage('Snapshot to nexus') {
        when {
            allOf {
                not { expression { params.RELEASE } };
                anyOf {
                    branch 'dev';
                    branch 'feature/*';
                }
            }
        }
        steps {
            withMaven (
                globalMavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.GlobalMavenSettingsConfig1387378707709',
                mavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig1396361652540',
                traceability: true){
                    sh 'mvn -B -DskipTests deploy'
                }
        }
    }
    stage('Maven release') {
      when {
          allOf {
              expression { params.RELEASE };
              branch 'master';
          }
      }
      environment {
          RELEASE_ARGS = utils.createReleaseArgs(params.RELEASE_VERSION, params.DEVELOPMENT_VERSION, params.DRY_RUN_RELEASE)
      }
      steps {
          withMaven(
            globalMavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.GlobalMavenSettingsConfig1387378707709',
            mavenSettingsConfig: 'org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig1396361652540',
            traceability: true) {
              git 'https://github.com/gbif/gbif-api.git'
              sh '''
                mvn -B -Dresume=false release:prepare release:perform $RELEASE_ARGS
                '''
            }
      }
    }
  }
    post {
      success {
        echo 'Pipeline executed successfully!'
      }
      failure {
        echo 'Pipeline execution failed!'
    }
  }
}
