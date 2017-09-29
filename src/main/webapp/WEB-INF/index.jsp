<%--
 *
 * Copyright 2017 Eonian Technologies.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
--%>
<%@include file="jspf/header.jspf" %> 

<h1>Echo API</h1>

<table>
    <tr>
        <td class="key">Version:</td>
        <td>${viewProperties['pom.version']}</td>
    </tr>
    <tr>
        <td>Group ID:</td>
        <td>${viewProperties['pom.groupId']}</td>
    </tr>
    <tr>
        <td>Artifact ID:</td>
        <td>${viewProperties['pom.artifactId']}</td>
    </tr>
    <tr>
        <td>Jenkins Build Number:</td>
        <td>${viewProperties['jenkins.build.number']}</td>
    </tr>
    <tr>
        <td>GIT Branch:</td>
        <td>${viewProperties['git.branch']}</td>
    </tr>
    <tr>
        <td>GIT Commit ID:</td>
        <td>${viewProperties['git.commit.id']}</td>
    </tr>
    <tr>
        <td>GIT Commit Message:</td>
        <td>${viewProperties['git.commit.message.full']}</td>
    </tr>
    <tr>
        <td>GIT Commit Username:</td>
        <td>${viewProperties['git.commit.user.name']}</td>
    </tr>
    <tr>
        <td>GIT Commit Time:</td>
        <td>${viewProperties['git.commit.time']}</td>
    </tr>
    <tr>
        <td>Spring Profiles:</td>
        <td>${viewProperties['activeSpringProfiles']}</td>
    </tr>
</table>

<%@include file="jspf/footer.jspf" %> 
