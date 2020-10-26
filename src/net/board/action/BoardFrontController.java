package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		System.out.println("요청 주소 : " + command);

		ActionForward forward = null;
		Action action = null;

		// 글쓰기 페이지 이동
		if (command.equals("/BoardWrite.bo"))
		{
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
		}

		 //	게시글 답글 페이지 이동
		else if (command.equals("/BoardReplyAction.bo"))
		{
			action = new BoardReplyView();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		
		// 게시글 삭제페이지 이동
		} else if (command.equals("/BoardDelete.bo"))
		{
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");
			
		// 게시글 수정페이지	
		} else if (command.equals("/BoardModify.bo"))
		{
			action = new BoardModifyView();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		// 게시글 등록
		} else if (command.equals("/BoardAddAction.bo"))
		{
			action = new BoardAddAction();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		// 게시글 답변 등록
		} else if (command.equals("/BoardReplyView.bo"))
		{
			action = new BoardReplyAction();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		// 게시글 수정
		} else if (command.equals("/BoardModifyAction.bo"))
		{
			action = new BoardModifyAction();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		// 게시글 삭제
		} else if (command.equals("/BoardDeleteAction.bo"))
		{
			action = new BoardDeleteAction();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		
		// 게시글 목록
		} else if (command.equals("/BoardList.bo"))
		{
			action = new BoardListAction();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		// 해당 게시글 조회
		} else if (command.equals("/BoardDetailAction.bo"))
		{
			action = new BoardDetailAction();
			try
			{
				forward = action.execute(request, response);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		if (forward.isRedirect())
		{
			response.sendRedirect(forward.getPath());
		} else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doProcess(request, response);
	}
}