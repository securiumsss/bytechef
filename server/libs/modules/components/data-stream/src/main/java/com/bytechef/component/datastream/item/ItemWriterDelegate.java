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

package com.bytechef.component.datastream.item;

import static com.bytechef.component.definition.datastream.ItemWriter.DESTINATION;

import com.bytechef.component.definition.datastream.ItemWriter;
import com.bytechef.platform.component.definition.ContextFactory;
import com.bytechef.platform.component.service.ClusterElementDefinitionService;
import com.bytechef.tenant.util.TenantUtils;
import java.util.Map;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

/**
 * @author Ivica Cardic
 */
public class ItemWriterDelegate extends AbstractItemDelegate implements ItemStreamWriter<Map<String, ?>> {

    private final ClusterElementDefinitionService clusterElementDefinitionService;
    private ItemWriter itemWriter;

    public ItemWriterDelegate(
        ClusterElementDefinitionService clusterElementDefinitionService, ContextFactory contextFactory) {

        super(DESTINATION, contextFactory);

        this.clusterElementDefinitionService = clusterElementDefinitionService;
    }

    @Override
    public void close() throws ItemStreamException {
        if (itemWriter != null) {
            itemWriter.close();
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        TenantUtils.runWithTenantId(
            tenantId, () -> itemWriter.open(
                inputParameters, connectionParameters,
                new ExecutionContextImpl(
                    contextFactory.createContext(componentName, null, editorEnvironment), executionContext)));
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        TenantUtils.runWithTenantId(tenantId, () -> itemWriter.update(
            new ExecutionContextImpl(
                contextFactory.createContext(componentName, null, editorEnvironment), executionContext)));
    }

    @Override
    public void write(Chunk<? extends Map<String, ?>> chunk) {
        TenantUtils.runWithTenantId(tenantId, () -> itemWriter.write(chunk.getItems()));
    }

    protected void doBeforeStep(final StepExecution stepExecution) {
        itemWriter = clusterElementDefinitionService.getClusterElement(
            componentName, componentVersion, clusterElementName);
    }
}
