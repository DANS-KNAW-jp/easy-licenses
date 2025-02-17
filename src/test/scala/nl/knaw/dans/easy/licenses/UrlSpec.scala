/**
 * Copyright (C) 2019 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.easy.licenses

import scala.collection.JavaConverters._

class UrlSpec extends LicenseJsonFixture with PropsFixture {

  "all the urls in licenses.json" should "be present in the properties (not vice versa)" in {
    val propUrls: Seq[String] = props.getKeys.asScala.map(toBaseUrl).toList
    val jsonUrls: Seq[String] = jsonMap.keys.map(toBaseUrl).toSeq

    forEvery(jsonUrls) { url => propUrls should contain(url) }
  }

  // external parties may change from http to https and/or drop/add www
  // easy-deposit-agreement-creator is insensitive for these changes
  // URLs with other protocols than http/https will remain as is
  private def toBaseUrl(fileName: String) = {
    fileName.replaceAll("https?://(www.)?", "")
  }
}
