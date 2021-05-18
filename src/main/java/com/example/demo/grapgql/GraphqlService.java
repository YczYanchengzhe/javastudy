package com.example.demo.grapgql;

import com.example.demo.grapgql.entity.Duration;
import com.example.demo.grapgql.entity.Step;
import com.example.demo.grapgql.entity.UserInfo;
import org.mountcloud.graphql.GraphqlClient;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.request.result.ResultAttributtes;
import org.mountcloud.graphql.response.GraphqlResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @ClassName: GraphqlClient
 * @Description: GraphqlClient
 * @Create by: A
 * @Date: 2021/4/27 22:35
 */
public class GraphqlService {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		String serverUrl = "http://127.0.0.1:12800/graphql";
		GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);
		//返回值类型:方法名
		String queryMethodName = "UserGroupCluster:getGroupList";
//		String queryMethodName = "services:searchServices";
		GraphqlQuery query = new DefaultGraphqlQuery(queryMethodName);
		//设置参数
		query.getRequestParameter()
				.addObjectParameter("userInfo",new UserInfo());
//				.addObjectParameter("duration", new Duration("2021-04-27 2237", "2021-04-27 2237", Step.MINUTE))
//				.addObjectParameter("keyword", "");
		//设置返回值
		query.addResultAttributes("oa");
		query.addResultAttributes(new ResultAttributtes("group"){{
			addResultAttributes("groupName","clusters");
		}});

		GraphqlResponse response = graphqlClient.doQuery(query);
		//获取数据，数据为map类型
		Map<Object, Object> result = response.getData();
		System.out.println(result);
	}

//	public static void main(String[] args) {
//		LocalDateTime localDateTime = LocalDateTime.now();
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
//		String resu = format.format(localDateTime);
//		System.out.println(resu);
//	}
}
