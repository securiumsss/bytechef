/*
 * Copyright 2025 ByteChef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.component.ods.file.action;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.FileEntry;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.ods.file.OdsFileComponentHandlerTest;
import com.bytechef.component.ods.file.constant.OdsFileConstants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author Ivica Cardic
 */
@Disabled
public class OdsFileReadActionTest {

    @Test
    public void testPerformReadODS() throws IOException, JSONException {
        ActionContext context = Mockito.mock(ActionContext.class);
        Parameters parameters = Mockito.mock(Parameters.class);

        // headerRow: true, includeEmptyCells: false, readAsString: false

        assertEquals(
            new JSONArray(getJSONObjectsWithNamedColumns(false, false)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        true, false, null, null, false, getFile("sample_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: true, includeEmptyCells: true, readAsString: false

        assertEquals(
            new JSONArray(getJSONObjectsWithNamedColumns(true, false)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        true, true, null, null, false, getFile("sample_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: true, includeEmptyCells: false, readAsString: true

        assertEquals(
            new JSONArray(getJSONObjectsWithNamedColumns(false, true)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        true, false, null, null, true, getFile("sample_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: true, includeEmptyCells: true, readAsString: true

        assertEquals(
            new JSONArray(getJSONObjectsWithNamedColumns(true, true)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        true, true, null, null, true, getFile("sample_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: false, includeEmptyCells: false, readAsString: false

        assertEquals(
            new JSONArray(getJSONArrayWithoutNamedColumns(false, false)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        false, false, null, null, false, getFile("sample_no_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: false, includeEmptyCells: false, readAsString: true

        assertEquals(
            new JSONArray(getJSONArrayWithoutNamedColumns(false, true)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        false, false, null, null, true, getFile("sample_no_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: false, includeEmptyCells: true, readAsString: false

        assertEquals(
            new JSONArray(getJSONArrayWithoutNamedColumns(true, false)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        false, true, null, null, false, getFile("sample_no_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // headerRow: false, includeEmptyCells: true, readAsString: true

        assertEquals(
            new JSONArray(getJSONArrayWithoutNamedColumns(true, true)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(
                        false, true, null, null, true, getFile("sample_no_header.ods"), parameters, context),
                    parameters, context)),
            true);

        // paging

        assertEquals(
            new JSONArray(getJSONObjectsWithNamedColumns(false, false).subList(0, 3)),
            new JSONArray(
                (List) OdsFileReadAction.perform(
                    getReadParameters(true, false, 1, 3, false, getFile("sample_header.ods"), parameters, context),
                    parameters, context)),
            true);
    }

    private File getFile(String filename) {
        return new File(OdsFileComponentHandlerTest.class
            .getClassLoader()
            .getResource("dependencies/ods-file/" + filename)
            .getFile());
    }

    @SuppressWarnings("PMD.SimplifiedTernary")
    private List<JSONObject> getJSONObjectsWithNamedColumns(boolean includeEmptyCells, boolean readAsString)
        throws JSONException {
        return List.of(
            getJSONObjectWithNamedColumns(
                readAsString ? "77.0" : 77, "A", "B", "C", readAsString ? "true" : true, "2021-12-07",
                readAsString ? "11.2" : 11.2),
            getJSONObjectWithNamedColumns(
                readAsString ? "4.0" : 4, "name1", "city1", "description1", readAsString ? "false" : false,
                includeEmptyCells ? "" : null, readAsString ? "12.0" : 12),
            getJSONObjectWithNamedColumns(
                readAsString ? "2.0" : 2, "A", "city2", includeEmptyCells ? "" : null, readAsString ? "true" : true,
                "2021-12-09", includeEmptyCells ? "" : null),
            getJSONObjectWithNamedColumns(
                readAsString ? "5678.0" : 5678, "ABCD", "city3", "EFGH", readAsString ? "false" : false,
                "2021-12-10", readAsString ? "13.23" : 13.23));
    }

    @SuppressWarnings("PMD.SimplifiedTernary")
    private List<JSONObject> getJSONArrayWithoutNamedColumns(boolean includeEmptyCells, boolean readAsString)
        throws JSONException {
        return List.of(
            getJSONObjectWithoutNamedColumns(
                readAsString ? "77.0" : 77, "A", "B", "C", readAsString ? "true" : true, "2021-12-07",
                readAsString ? "11.2" : 11.2),
            getJSONObjectWithoutNamedColumns(
                readAsString ? "4.0" : 4, "name1", "city1", "description1", readAsString ? "false" : false,
                includeEmptyCells ? "" : null, readAsString ? "12.0" : 12),
            getJSONObjectWithoutNamedColumns(
                readAsString ? "2.0" : 2, "A", "city2", includeEmptyCells ? "" : null, readAsString ? "true" : true,
                "2021-12-09", includeEmptyCells ? "" : null),
            getJSONObjectWithoutNamedColumns(
                readAsString ? "5678.0" : 5678, "ABCD", "city3", "EFGH", readAsString ? "false" : false,
                "2021-12-10", readAsString ? "13.23" : 13.23));
    }

    private JSONObject getJSONObjectWithNamedColumns(
        Object id, String name, String city, String description, Object active, String date, Object sum)
        throws JSONException {
        return getJSONObject(
            "id", id, "name", name, "city", city, "description", description, "active", active, "date", date,
            "sum", sum);
    }

    private JSONObject getJSONObjectWithoutNamedColumns(
        Object id, String name, String city, String description, Object active, String date, Object sum)
        throws JSONException {
        return getJSONObject(
            "column_1", id, "column_2", name, "column_3", city, "column_4", description, "column_5", active,
            "column_6", date, "column_7", sum);
    }

    private JSONObject getJSONObject(
        String idKey, Object idValue, String nameKey, String nameValue, String cityKey, String cityValue,
        String descriptionKey, String descriptionValue, String activeKey, Object activeValue, String dateKey,
        Object dateValue, String sumKey, Object sumValue)
        throws JSONException {

        JSONObject jsonObject = new JSONObject()
            .put(idKey, idValue)
            .put(nameKey, nameValue)
            .put(cityKey, cityValue)
            .put(activeKey, activeValue)
            .put(dateKey, dateValue)
            .put(sumKey, sumValue);

        if (descriptionValue != null) {
            jsonObject.put(descriptionKey, descriptionValue);
        }

        return jsonObject;
    }

    @SuppressFBWarnings("OBL")
    private Parameters getReadParameters(
        boolean headerRow, boolean includeEmptyCells, Integer pageNumber, Integer pageSize, boolean readAsString,
        File file, Parameters parameters, ActionContext context)
        throws FileNotFoundException {

        Mockito
            .when(parameters.getRequired(Mockito.eq(OdsFileConstants.FILE_ENTRY),
                Mockito.eq(FileEntry.class)))
            .thenReturn(Mockito.mock(FileEntry.class));
        Mockito.when(parameters.getBoolean(Mockito.eq(OdsFileConstants.HEADER_ROW), Mockito.eq(true)))
            .thenReturn(headerRow);
        Mockito.when(parameters.getBoolean(Mockito.eq(OdsFileConstants.INCLUDE_EMPTY_CELLS), Mockito.eq(false)))
            .thenReturn(includeEmptyCells);
        Mockito.when(parameters.getInteger(Mockito.eq(OdsFileConstants.PAGE_NUMBER)))
            .thenReturn(pageNumber);
        Mockito.when(parameters.getInteger(Mockito.eq(OdsFileConstants.PAGE_SIZE)))
            .thenReturn(pageSize);
        Mockito.when(parameters.getBoolean(
            Mockito.eq(OdsFileConstants.READ_AS_STRING), Mockito.eq(false)))
            .thenReturn(readAsString);

        if (file != null) {
            Mockito.when(context.file(file1 -> file1.getStream(Mockito.any(FileEntry.class))))
                .thenReturn(new FileInputStream(file));
        }

        return parameters;
    }
}
