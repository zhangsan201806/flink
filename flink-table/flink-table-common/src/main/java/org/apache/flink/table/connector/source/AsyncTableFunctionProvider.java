/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.connector.source;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.table.functions.AsyncTableFunction;

/**
 * Provider of an {@link AsyncTableFunction} instance as a runtime implementation for {@link LookupTableSource}.
 *
 * <p>The runtime will call the function with values describing the table's lookup keys (in the order
 * of declaration in {@link LookupTableSource.LookupContext#getKeys()}).
 */
@PublicEvolving
public interface AsyncTableFunctionProvider<T> extends LookupTableSource.LookupRuntimeProvider {

	/**
	 * Helper method for creating a static provider.
	 */
	static <T> AsyncTableFunctionProvider<T> of(AsyncTableFunction<T> asyncTableFunction) {
		return () -> asyncTableFunction;
	}

	/**
	 * Creates a {@link AsyncTableFunction} instance.
	 */
	AsyncTableFunction<T> createAsyncTableFunction();
}
