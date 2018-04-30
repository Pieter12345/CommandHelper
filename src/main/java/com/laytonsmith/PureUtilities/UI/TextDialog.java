package com.laytonsmith.PureUtilities.UI;

import com.laytonsmith.PureUtilities.Common.ReflectionUtils;
import com.laytonsmith.PureUtilities.Common.UIUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * A TextDialog is a JDialog that supports basic HTML text formatting. It comes with two main UI components, a
 * JEditorPane that has been configured to support html text, and an "Ok" button which closes the dialog. The text
 * supports some features that the JEditorPane doesn't normally support, namely link handling. URLs to an external site
 * will work as expected, opening the user's web browser. Internal links are also supported, for scrolling to sections
 * with a specific id. Those links should look like <code>&lt;a href="#section1"&gt;Link&lt;/a&gt;</code>, and some
 * element elsewhere should have the id "section1".
 */
public class TextDialog extends javax.swing.JDialog {

	/**
	 * Creates new TextDialog. The dialog box provides a window to show simple stylized text.
	 *
	 * @param parent The parent window
	 * @param modal Whether or not this is modal
	 * @param text The text to show in the box. This should be html text.
	 */
	public TextDialog(java.awt.Frame parent, boolean modal, String text) {
		super(parent, modal);
		initComponents();
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextDialog.this.setVisible(false);
				TextDialog.this.dispose();
			}
		});
		inputDialog.setContentType("text/html");
		inputDialog.setEditable(false);
		HTMLDocument doc = (HTMLDocument) inputDialog.getDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) inputDialog.getEditorKit();
		try {
			editorKit.insertHTML(doc, doc.getLength(), "<html>" + text + "</html>", 0, 0, null);
		} catch(BadLocationException | IOException ex) {
			Logger.getLogger(TextDialog.class.getName()).log(Level.SEVERE, null, ex);
		}
		inputDialog.setCaretPosition(0);
		inputDialog.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					URL url = e.getURL();
					if(url == null) {
						// This is an internal link. The url will be null if the url
						// is "invalid", which also includes anchor links. In order
						// to work around this, we have to get messy and use some
						// reflection stuff to grab what we actually need.
						HTMLDocument doc = (HTMLDocument) inputDialog.getDocument();
						Element clicked = e.getSourceElement();
						Enumeration enu = clicked.getAttributes().getAttributeNames();
						Object[] attr = (Object[]) ReflectionUtils.get(enu.getClass(), enu, "attr");
						String link = null;
						for(Object item : attr) {
							if(item instanceof SimpleAttributeSet) {
								SimpleAttributeSet tag = (SimpleAttributeSet) item;
								@SuppressWarnings("UseOfObsoleteCollectionType")
								Hashtable table = (Hashtable) ReflectionUtils.get(tag.getClass(), tag, "table");
								for(Object key : table.keySet()) {
									if(key instanceof HTML.Attribute) {
										if("href".equals(((HTML.Attribute) key).toString())) {
											link = (String) table.get(key);
											break;
										}
									}
								}
								break;
							}
						}
						if(link != null) {
							String id = link.substring(1);
							Element elem = doc.getElement(id);
							if(elem != null) {
								inputDialog.setCaretPosition(elem.getStartOffset());
								inputDialog.scrollToReference(id);
							}
						}
					} else {
						try {
							// It's an external link
							UIUtils.openWebpage(url);
						} catch(IOException | URISyntaxException ex) {
							Logger.getLogger(TextDialog.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}
		});
	}

	/**
	 * Sets the OK button text.
	 *
	 * @param text
	 */
	public void setOKButtonText(String text) {
		okButton.setText(text);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		okButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		inputDialog = new javax.swing.JEditorPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		okButton.setText("Ok");
		okButton.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		okButton.setSelected(true);

		jScrollPane2.setAutoscrolls(true);

		inputDialog.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		inputDialog.setMinimumSize(new java.awt.Dimension(20, 10));
		jScrollPane2.setViewportView(inputDialog);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
				.addContainerGap())
			.addGroup(jPanel1Layout.createSequentialGroup()
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(okButton)
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(okButton)
				.addContainerGap())
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				TextDialog td = new TextDialog(null, false, "<font color=\"red\">Hi</font>");
				UIUtils.centerWindow(td);
				td.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JEditorPane inputDialog;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton okButton;
	// End of variables declaration//GEN-END:variables
}
