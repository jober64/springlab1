//@Library('pipeline-library') _
node('maven3') {

  def serviceName = 'springlab1'
  def gitURL      = 'https://github.com/jober64/springlab1.git'

  def envDev      = 'mvc'

  //def utils = new se.seb.build.Utils()

  stage('Checkout') {
    git url: "${gitURL}"
  }

  //def version = utils.getPomVersion()

  stage('Build') {
    sh "mvn -q -s settings.xml -DskipTests package"
  }

  stage('Test and Analysis') {
    parallel (
      'Test': {
        sh 'mvn -s settings.xml test'
      },
      'Static Analysis': {
        echo 'PLACEHOLDER <sonarqube>'
      }
    )
  }

  stage('Build and Deploy DEV') {
    sh "oc project ${envDev}"

    sh "oc start-build ${serviceName} --from-file target/*.jar -n ${envDev} --follow"
    //sh "oc tag ${envDev}/${serviceName}:latest ${envDev}/${serviceName}:${version}"

    //openshiftDeploy(deploymentConfig: "${serviceName}", namespace: "${envDev}")
    //openshiftVerifyDeployment(deploymentConfig: "${serviceName}", namespace: "${envDev}")
  }

}
